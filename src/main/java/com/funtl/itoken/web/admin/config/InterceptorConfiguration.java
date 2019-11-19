package com.funtl.itoken.web.admin.config;

import com.funtl.itoken.web.admin.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer{

    @Bean
     LoginInterceptor loginInterceptor(){
        System.out.println("注入bean...");
        return new LoginInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(
                loginInterceptor())//注意：这里如果使用new LoginInterceptor()导致不能注入redisService
                .addPathPatterns("/**")
                .excludePathPatterns("/static");
    }
}
