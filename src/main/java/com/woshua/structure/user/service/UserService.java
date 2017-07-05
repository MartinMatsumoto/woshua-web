package com.woshua.structure.user.service;

import com.woshua.structure.user.domain.User;
import com.woshua.structure.user.transferobj.UserTo;

import javax.servlet.http.Cookie;

/**
 * Created by Acer on 2017/3/27.
 */
public interface UserService {

    public User login(String account, String password, String ip);

    public User authCookieUser(Cookie[] cookies, String ip);

    public User findById(Long id);

    public boolean authExist(String account, String email);

    public UserTo register(String account, String password, String email, String nickname, int sex, int usertype, String ip);
}
