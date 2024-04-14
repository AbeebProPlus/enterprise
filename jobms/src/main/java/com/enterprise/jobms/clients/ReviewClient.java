package com.enterprise.jobms.clients;


import com.enterprise.jobms.data.dto.ReviewDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "REVIEW-SERVICE", path = "/reviews", url = "${review-service.url}")
public interface ReviewClient {
    @GetMapping
    ResponseEntity<List<ReviewDto>> getAllReviews(@RequestParam Long companyId);
}
