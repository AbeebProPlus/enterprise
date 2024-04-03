package com.enterprise.reviewms.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReviewConfiguration {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}