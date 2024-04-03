package com.enterprise.jobms.service;


import com.enterprise.jobms.data.dto.JobDto;
import com.enterprise.jobms.data.model.Job;

import java.util.List;

public interface JobService {
    List<JobDto> findAll();
    void createJob(Job job);

    JobDto getJobById(Long id);

    boolean deleteJobById(Long id);

    boolean updateJob(Long id, Job updatedJob);
}
