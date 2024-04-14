package com.enterprise.companyms.feignClients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "REVIEW-SERVICE", path="/reviews", url = "${review-service.url}")
public interface ReviewClient {
    @GetMapping("/average-rating")
    Double averageRating(@RequestParam Long companyId);
}
