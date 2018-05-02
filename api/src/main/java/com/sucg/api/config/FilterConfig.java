package com.sucg.api.config;

import com.sucg.springclouddemo.filter.StartApplicationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean filterRegist() {
        FilterRegistrationBean frBean = new FilterRegistrationBean();
        frBean.setFilter(new StartApplicationFilter());
        frBean.addUrlPatterns("/*");
        return frBean;
    }
}
