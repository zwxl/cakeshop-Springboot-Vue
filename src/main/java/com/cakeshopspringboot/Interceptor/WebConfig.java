package com.cakeshopspringboot.Interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author 张伟雄
 * @Date 2022/12/12
 **/
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public HandleInterceptor getHandleInterceptor(){
        return new HandleInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getHandleInterceptor()).addPathPatterns("/user/login");
    }
}
