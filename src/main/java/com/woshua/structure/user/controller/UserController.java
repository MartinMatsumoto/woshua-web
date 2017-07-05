package com.woshua.structure.user.controller;

import com.woshua.core.web.BaseController;
import com.woshua.core.web.ErrorCode;
import com.woshua.core.web.Header;
import com.woshua.core.web.WebJsonView;
import com.woshua.structure.user.domain.User;
import com.woshua.structure.user.service.UserService;
import com.woshua.structure.user.transferobj.UserTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Acer on 2017/3/24.
 */
@Controller    // This means that this class is a Controller
@RequestMapping(path = "/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/login")
    public
    @ResponseBody
    ModelAndView login(@RequestParam String account
            , @RequestParam String password, HttpServletRequest request) {
        User user = userService.login(account, password, getIpAddress(request));
        if(user == null){
            return new ModelAndView(new WebJsonView(new Header(ErrorCode.ERRORCODE_LOGIN_ERROR)));
        }
        return new ModelAndView(new WebJsonView(new UserTo(user)));
    }

    @PostMapping(path = "/auth_exist")
    public
    @ResponseBody
    ModelAndView authExist(@RequestParam String account
            , @RequestParam String email, HttpServletRequest request) {
        boolean exist = userService.authExist(account, email);
        if (exist) {
            return new ModelAndView(new WebJsonView(new Header(ErrorCode.ERRORCODE_REGISTER_EXIST)));
        } else {
            return new ModelAndView(new WebJsonView());
        }
    }

    @PostMapping(path = "/register")
    public
    @ResponseBody
    ModelAndView register(@RequestParam String account
            , @RequestParam String password, @RequestParam String email, @RequestParam String nickname, @RequestParam int sex, @RequestParam int usertype, HttpServletRequest request) {
        UserTo user = userService.register(account, password, email, nickname, sex, usertype, getIpAddress(request));
        if (user == null) {
            return new ModelAndView(new WebJsonView(new Header(ErrorCode.ERRORCODE_REGISTER_ERROR)));
        } else {
            return new ModelAndView(new WebJsonView(user));
        }
    }

    /*@GetMapping(value = "/header")
    public ModelAndView praxisHeader(HttpServletRequest request) {
        User user = userService.authCookieUser(request.getCookies(),getIpAddress(request));
        return new ModelAndView("praxis/include/header", "user", user);
    }*/
}
