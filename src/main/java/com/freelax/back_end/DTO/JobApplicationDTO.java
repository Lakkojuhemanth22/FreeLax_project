package com.freelax.back_end.DTO;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobApplicationDTO {
    private Long jobId;
    private Long freelancerId;
    private String coverLetter;
    private String resume;
    private Double proposedBudget;



    // Getters and setters
}