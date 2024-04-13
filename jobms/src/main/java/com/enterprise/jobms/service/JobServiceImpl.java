package com.enterprise.jobms.service;


import com.enterprise.jobms.clients.CompanyClient;
import com.enterprise.jobms.clients.ReviewClient;
import com.enterprise.jobms.data.dto.CompanyDto;
import com.enterprise.jobms.data.dto.JobDto;
import com.enterprise.jobms.data.dto.ReviewDto;
import com.enterprise.jobms.data.model.Job;
import com.enterprise.jobms.repo.JobRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;
    private final ModelMapper modelMapper;
    private final CompanyClient companyClient;
    private final ReviewClient reviewClient;
    int attempt = 0;


    public List<JobDto> findAll() {
        List<Job> jobs = jobRepository.findAll();
        return jobs.stream().map(job -> modelMapper.map(job, JobDto.class)).toList();
    }


    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
//    @CircuitBreaker(name = "companyBreaker",
//            fallbackMethod="companyBreakerFallback")
//    @Retry(name = "companyBreaker",
//            fallbackMethod="companyBreakerFallback")

    @RateLimiter(name = "companyBreaker", fallbackMethod = "companyBreakerFallback")
    public JobDto getJobById(Long id) {
//        System.out.println("attempt: " + ++attempt);
        Optional<Job> jobOptional = jobRepository.findById(id);
        System.out.print("Get job by id");
        if (jobOptional.isPresent()) {
            Job job = jobOptional.get();
            JobDto jobDto = modelMapper.map(job, JobDto.class);
            CompanyDto companyDto = companyClient.getCompany(job.getCompanyId()).getBody();
            jobDto.setCompany(companyDto);
            List<ReviewDto> reviewDtos = reviewClient.getAllReviews(job.getId()).getBody();
            jobDto.setReviews(reviewDtos);
            return jobDto;
        }
        return null;
    }
    public JobDto companyBreakerFallback(Exception e){
        JobDto jobDto = new JobDto();
        jobDto.setTitle("Dummy");
        return jobDto;
    }

    @Override
    public boolean deleteJobById(Long id) {
        try {
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateJob(Long id, Job updatedJob) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if (jobOptional.isPresent()) {
            Job job = jobOptional.get();
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setLocation(updatedJob.getLocation());
            jobRepository.save(job);
            return true;
        }
        return false;
    }
}