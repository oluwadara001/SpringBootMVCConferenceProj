package com.YomiOluwadara.conferencedemo.Config;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Register the data directory as a static resource location
        Path dataPath = Paths.get("data").toAbsolutePath().normalize();
        String dataPathLocation = dataPath.toString().replace("\\", "/");
        
        if (!dataPathLocation.endsWith("/")) {
            dataPathLocation += "/";
        }
        
        registry.addResourceHandler("/data/**")
                .addResourceLocations("file:" + dataPathLocation);
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        // Enable URL path normalization to handle double slashes
        configurer.setUseTrailingSlashMatch(true);
        configurer.setUseRegisteredSuffixPatternMatch(true);
    }
} 