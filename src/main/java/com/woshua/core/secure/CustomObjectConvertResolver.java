/**
 * @(#)ObjectConvertInterceptor.java Copyright 2013 VISION, Inc. All rights reserved.
 */
package com.woshua.core.secure;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * description
 *
 * @author fengyuan
 * @version 1.0, 2011-4-27
 */
@Configuration
public class CustomObjectConvertResolver implements WebArgumentResolver {

    @Override
    public Object resolveArgument(MethodParameter methodParameter, NativeWebRequest webRequest) throws Exception {
        Object[] paramAnnos = methodParameter.getParameterAnnotations();

        return UNRESOLVED;
    }

}
