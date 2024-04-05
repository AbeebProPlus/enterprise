package com.enterprise.jobms.service;


import com.enterprise.jobms.clients.CompanyClient;
import com.enterprise.jobms.clients.ReviewClient;
import com.enterprise.jobms.data.dto.CompanyDto;
import com.enterprise.jobms.data.dto.JobDto;
import com.enterprise.jobms.data.dto.ReviewDto;
import com.enterprise.jobms.data.model.Job;
import com.enterprise.jobms.repo.JobRepository;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;
    private final ModelMapper modelMapper;
    private final CompanyClient companyClient;
    private final ReviewClient reviewClient;


    public List<JobDto> findAll() {
        List<Job> jobs = jobRepository.findAll();
        return jobs.stream().map(job -> modelMapper.map(job, JobDto.class)).toList();
    }


    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public JobDto getJobById(Long id) {
        Optional<Job> jobOptional = jobRepository.findById(id);
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