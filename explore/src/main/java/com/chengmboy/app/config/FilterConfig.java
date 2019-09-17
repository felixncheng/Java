package com.chengmboy.app.config;

import com.chengmboy.app.filter.LogFilter;
import com.chengmboy.app.filter.LogFilter2;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author cheng_mboy
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean logFilter() {
        System.out.println("logFilter Registration");
        FilterRegistrationBean<LogFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new LogFilter());
        registrationBean.setOrder(1);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean logFilter2() {
        System.out.println("logFilter2 Registration");
        FilterRegistrationBean<LogFilter2> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new LogFilter2());
        registrationBean.setOrder(2);
        return registrationBean;
    }
}
