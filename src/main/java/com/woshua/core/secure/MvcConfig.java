package com.woshua.core.secure;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableSpringDataWebSupport
public class MvcConfig extends WebMvcConfigurerAdapter {
    
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

}
