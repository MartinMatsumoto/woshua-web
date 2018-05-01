package com.woshua.core.secure;

import org.apache.commons.lang3.ArrayUtils;
import org.jboss.logging.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author YeChunBo
 * @time 2017年9月8日
 * <p>
 * 类说明: ip 拦截器，只有在配置文件中定义了的ip 才可以访问接口
 */

public class LoginInterceptor implements HandlerInterceptor {

    private final Logger logger = Logger.getLogger(LoginInterceptor.class);

    private final String[] interceptUrls = {"/praxis/space"};

    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {

    }

    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {

    }

    /**
     * 在请求处理之前进行调用（Controller方法调用之前）调用,
     * 返回true 则放行， false 则将直接跳出方法
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
        String path = request.getServletPath();
        return willIntercept(path, response);
    }

    private boolean willIntercept(String path, HttpServletResponse response) throws Exception {
        if (ArrayUtils.contains(interceptUrls, path)) {
            response.sendRedirect("/");
            return false;
        }
        return true;
    }

}