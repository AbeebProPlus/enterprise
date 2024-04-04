package com.enterprise.jobms.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class JobDto {
    private String title;
    private String description;
    private String minSalary;
    private String maxSalary;
    private String location;
    private CompanyDto company;
    private List<ReviewDto> reviews;
}
