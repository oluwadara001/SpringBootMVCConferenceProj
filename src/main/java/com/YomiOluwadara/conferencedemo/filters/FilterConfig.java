package com.YomiOluwadara.conferencedemo.filters;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration
public class FilterConfig {
    
    @Bean
    public FilterRegistrationBean<UrlNormalizationFilter> urlNormalizationFilter() {
        FilterRegistrationBean<UrlNormalizationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new UrlNormalizationFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return registrationBean;
    }
} 