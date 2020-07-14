package com.example.demo.config;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 *
 * */
public class MyWebAppConfigurer implements WebMvcConfigurer {

    //静态资源处理
    //    addResoureHandler：指的是对外暴露的访问路径（访问时候的路径 例如：localhost:80:/temp-rainy/a.png）
    //    addResourceLocations：指的是内部文件放置的目录（就是真正存放文件的路径）
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/temp-rainy/**").addResourceLocations("D:/temp-rainy/");
    }

}
