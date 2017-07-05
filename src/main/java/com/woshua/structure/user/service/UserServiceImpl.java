package com.woshua.structure.user.service;

import com.woshua.core.enums.SexType;
import com.woshua.core.enums.UserType;
import com.woshua.core.utils.Constant;
import com.woshua.core.utils.CookieUtils;
import com.woshua.structure.user.domain.User;
import com.woshua.structure.user.repository.UserRepository;
import com.woshua.structure.user.transferobj.UserTo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;

/**
 * Created by Acer on 2017/3/27.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public User login(String account, String password, String ip) {
        User user = userRepository.findByAccountAndPassword(account, password);

        if (user != null) {
            user.setPassword(CookieUtils.createCookieKey(user.getId(), user.getAccount(), ip));
        }
        return user;
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public User authCookieUser(Cookie[] cookies, String ip) {
        Cookie idCookie = CookieUtils.getCookie(cookies, CookieUtils.ID_NAME);
        Cookie keyCookie = CookieUtils.getCookie(cookies, CookieUtils.KEY_NAME);
        Cookie accountCookie = CookieUtils.getCookie(cookies, CookieUtils.ACCOUNT_NAME);

        if (idCookie != null && keyCookie != null && accountCookie != null) {
            Long id = Long.parseLong(idCookie.getValue());
            String key = keyCookie.getValue();
            String account = accountCookie.getValue();

            String newKey = CookieUtils.createCookieKey(id, account, ip);
            if (!newKey.equals(key)) {
                return null;
            }

            User user = findById(id);
            if (user != null) {
                return user;
            }
        }

        return null;
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public User findById(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public boolean authExist(String account, String email) {
        User user = userRepository.findByEmailOrAccount(email, account);
        if (user != null) {
            return true;
        }
        return false;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public UserTo register(String account, String password, String email, String nickname, int sex, int usertype, String ip) {
        boolean exist = authExist(account, email);
        User user = null;
        UserTo userTo = null;
        if (!exist) {
            user = new User();
            user.setAccount(account);
            user.setPassword(password);
            user.setEmail(email);
            user.setNickname(nickname);
            user.setSex(SexType.get(sex));
            user.setUsertype(UserType.get(usertype));
            boolean auth = authRegister(user);
            if (auth) {
                user = userRepository.save(user);
                if (user != null) {
                    userTo = new UserTo(user);
                    userTo.setPassword(CookieUtils.createCookieKey(user.getId(), user.getAccount(), ip));
                }
            }
        }
        return userTo;
    }

    private boolean authRegister(User user) {
        if (StringUtils.isEmpty(user.getAccount())) {
            return false;
        }

        if (StringUtils.isEmpty(user.getEmail())) {
            return false;
        }

        if (StringUtils.isEmpty(user.getPassword())) {
            return false;
        }

        if (StringUtils.isEmpty(user.getNickname())) {
            return false;
        }

        if (user.getUsertype() == null) {
            return false;
        }

        if (user.getSex() == null) {
            return false;
        }

        if (StringUtils.isEmpty(user.getIconPath())) {
            if (UserType.TEACHER_TYPE.equals(user.getUsertype())) {
                user.setIconPath(Constant.TEACHER_HEADER_PATH);
            } else if (UserType.STUDENT_TYPE.equals(user.getUsertype())) {
                user.setIconPath(Constant.STUDENT_HEADER_PATH);
            } else if (UserType.OTHERS_TYPE.equals(user.getUsertype())) {
                user.setIconPath(Constant.PARENT_HEADER_PATH);
            }

        }

        return true;
    }

}
