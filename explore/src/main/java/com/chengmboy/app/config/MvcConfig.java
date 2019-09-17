package com.chengmboy.app.config;


import com.chengmboy.app.interceptor.LogInterceptor;
import com.chengmboy.app.interceptor.UserInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author cheng_mboy
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserInterceptor());
        registry.addInterceptor(new LogInterceptor());
    }

}
