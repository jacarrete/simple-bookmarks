package com.simple.bookmarks.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
/**
 * Created by jcarretero on 22/01/2018.
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/*.js/**").addResourceLocations("/ui/static/");
        registry.addResourceHandler("/*.css/**").addResourceLocations("/ui/static/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/login").setViewName("login");
    }

//    @Bean
//    public InternalResourceViewResolver setupViewResolver()  {
//        InternalResourceViewResolver resolver =  new InternalResourceViewResolver();
//        resolver.setPrefix ("/ui/jsp/");
//        resolver.setSuffix (".jsp");
//        resolver.setViewClass (JstlView.class);
//        return resolver;
//    }

}
