package com.enterprise.companyms.service;

import com.enterprise.companyms.data.dto.ReviewMessage;
import com.enterprise.companyms.data.models.Company;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();
    boolean updateCompany(Company company, Long id);
    void createCompany(Company company);
    boolean deleteCompanyById(Long id);
    Company getCompanyById(Long id);
    void updateCompanyRating(ReviewMessage reviewMessage);
}
