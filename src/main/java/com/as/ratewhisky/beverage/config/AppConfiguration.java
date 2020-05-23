package com.as.ratewhisky.beverage.config;

import com.as.ratewhisky.beverage.converter.BeverageToBeverageResponseConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfiguration implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new BeverageToBeverageResponseConverter());
    }

}
