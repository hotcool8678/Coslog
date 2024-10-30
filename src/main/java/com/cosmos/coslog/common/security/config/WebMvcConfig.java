package com.cosmos.coslog.common.security.config;

import com.cosmos.coslog.common.security.handler.HeaderInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebMvcConfig implements WebMvcConfigurer {
    public static final String[] xUrls = {"/login", "/logout"};
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getHeaderInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(xUrls);
    }

    public HeaderInterceptor getHeaderInterceptor(){
        return new HeaderInterceptor();
    }
}
