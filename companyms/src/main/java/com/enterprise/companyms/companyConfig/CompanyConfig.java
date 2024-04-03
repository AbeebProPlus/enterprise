package com.enterprise.companyms.companyConfig;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CompanyConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
