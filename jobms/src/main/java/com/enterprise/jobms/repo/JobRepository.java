package com.enterprise.jobms.repo;


import com.enterprise.jobms.data.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long>{

}