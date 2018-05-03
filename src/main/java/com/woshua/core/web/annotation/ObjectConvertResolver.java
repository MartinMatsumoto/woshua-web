package com.woshua.core.web.annotation;

import javax.servlet.http.HttpServletRequest;

import com.woshua.core.utils.IPUtils;
import com.woshua.core.web.ObjectConvertAnno;
import com.woshua.structure.operator.domain.Operator;
import com.woshua.structure.user.domain.User;
import com.woshua.structure.user.service.UserService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;


@Configuration
public class ObjectConvertResolver
        implements
        HandlerMethodArgumentResolver,
        ApplicationContextAware {
    private static boolean isReloaded = false;
    private static ApplicationContext applicationContext;

    private UserService userService;

    /**
     * 判断是否支持要转换的参数类型
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(ObjectConvertAnno.class) != null;
    }

    /**
     * 当supportsParameter返回true,支持后进行相应的转换
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Object[] paramAnnos = parameter.getParameterAnnotations();
        if (paramAnnos == null || paramAnnos.length == 0) {
            return new Object();
        }
        for (Object anno : paramAnnos) {
            if (!ObjectConvertAnno.class.isInstance(anno)) {
                continue;
            }
            ObjectConvertAnno objectConvertAnno = (ObjectConvertAnno) anno;
            Object object = execute(parameter, webRequest);
            if (objectConvertAnno.required() && object == null) {
                return null;
            }
            return object;
        }
        return new Object();
    }

    private Object execute(MethodParameter methodParameter, NativeWebRequest webRequest) {
        Class<?> paramType = methodParameter.getParameterType();
        if (paramType.equals(User.class)) {

            return getCurrentUser((HttpServletRequest) webRequest.getNativeRequest());
        } else if (paramType.equals(Operator.class)) {
            return getCurrentOperator((HttpServletRequest) webRequest.getNativeRequest());
        }
        return null;
    }

    /**
     * 获取当前用户
     *
     * @return
     */
    private User getCurrentUser(HttpServletRequest request) {
        synchronized (ObjectConvertResolver.class) {
            while (!isReloaded) {
                try {
                    ObjectConvertResolver.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // TODO 这里写业务
            userService = ObjectConvertResolver.applicationContext
                    .getBean(UserService.class);
            return userService.authCookieUser(request.getCookies(), IPUtils.getIpAddress(request));
        }
    }

    /**
     * 获取当前登陆的第三方用户
     *
     * @return
     */
    private Operator getCurrentOperator(HttpServletRequest request) {
        synchronized (ObjectConvertResolver.class) {
            while (!isReloaded) {
                try {
                    ObjectConvertResolver.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        synchronized (ObjectConvertResolver.class) {
            ObjectConvertResolver.applicationContext = applicationContext;
            isReloaded = true;
            ObjectConvertResolver.class.notifyAll();
        }
    }
}