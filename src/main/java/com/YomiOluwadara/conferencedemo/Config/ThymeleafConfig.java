package com.YomiOluwadara.conferencedemo.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

/**
 * This is an optional class because Spring Boot will automatically configure Thymeleaf with sensible defaults.
 * However, if you need to customize Thymeleaf configuration,
 */
@Configuration
public class ThymeleafConfig {

    @Bean
    public ViewResolver viewResolver(SpringTemplateEngine templateEngine) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine);
        return resolver;
    }
}
