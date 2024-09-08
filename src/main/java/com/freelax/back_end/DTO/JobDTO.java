package com.freelax.back_end.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class JobDTO {
    private Long id;
    private String JobName;
    private String description;
    private String companyName;
    private List<String> skillsRequired;
    private String location; // Optional: add location if needed
    private Date postedDate;
    private String employmentType; // Full-time, Part-time, Contract, etc.
    private String jobCategory; // Category of the job (e.g., Software Development, Design)
    private Double salary;
}
