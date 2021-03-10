package com.mc.mall.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
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
                //默认拦截所有
                .addPathPatterns("/**")
                //配置不拦截的url
                .excludePathPatterns("/error","/user/login", "/user/register",
                        "/categores", "/products", "/products/*", "/swagger-ui.html/**"

                );
    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
//        registry.addResourceHandler("swagger-ui.html")
//                .addResourceLocations("classpath:/META-INF/resources/");
//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/");
//                .addResourceLocations("classpath:/META-INF/resources/webjars/");
//    }
}
