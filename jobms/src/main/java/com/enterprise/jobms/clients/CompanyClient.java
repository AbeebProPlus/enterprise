package com.enterprise.jobms.clients;

import com.enterprise.jobms.data.dto.CompanyDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "COMPANY-SERVICE", path = "/companies")
public interface CompanyClient {
    @GetMapping("/{id}")
    ResponseEntity<CompanyDto> getCompany(@PathVariable Long id);
}
