package com.enterprise.companyms.repo;

import com.enterprise.companyms.data.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
