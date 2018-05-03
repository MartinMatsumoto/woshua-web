package com.woshua.core.secure;

import com.woshua.core.web.annotation.ObjectConvertResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
@EnableSpringDataWebSupport
public class MvcConfig extends WebMvcConfigurerAdapter {

    //需要拦截的地址
    private final String[] interceptUrls = {"/praxis/space", "/praxis/zujuan"};

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("praxis/index");
        registry.addViewController("/praxis/mianze").setViewName("praxis/mianze");
        registry.addViewController("/praxis/fuwu").setViewName("praxis/fuwu");
        registry.addViewController("/praxis/contact").setViewName("praxis/contact");

        //registry.addViewController("/praxis/tiku").setViewName("praxis/tiku");
        registry.addViewController("/praxis/space").setViewName("praxis/space");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/praxis/**").addResourceLocations("classpath:/praxis/");
        registry.addResourceHandler("/global/**").addResourceLocations("classpath:/global/");
        super.addResourceHandlers(registry);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getLoginIntercptor()).addPathPatterns(interceptUrls);
        super.addInterceptors(registry);
    }

    @Bean
    public LoginInterceptor getLoginIntercptor() {
        return new LoginInterceptor();
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new ObjectConvertResolver());
    }
}
