package com.enterprise.jobms.config;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
