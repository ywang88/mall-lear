package com.mc.mall.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截过滤器
 * Author wangyi
 * Create 2020-11-18 21:32
 * ClassName InterceptorConfig
 * Version 1.0
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册拦截器
        registry.addInterceptor(new UserLoginInterceptor())
                //拦截所有
                .addPathPatterns("/**")
                //配置不拦截的url
                .excludePathPatterns("/user/login", "/user/register", "/categores", "/products");
    }
}
