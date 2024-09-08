package com.freelax.back_end.Services;

import com.freelax.back_end.DTO.JobApplicationDTO;
import com.freelax.back_end.Entity.Freelancer;
import com.freelax.back_end.Entity.Job;
import com.freelax.back_end.Entity.JobApplication;
import com.freelax.back_end.Repository.FreelancerRepository;
import com.freelax.back_end.Repository.JobApplicationRepository;
import com.freelax.back_end.Repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JobApplicationService {

    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private FreelancerRepository freelancerRepository;

    public boolean applyForJob(JobApplicationDTO applicationDTO) {
        // Validate applicationDTO fields
        if (applicationDTO.getJobId() == null || applicationDTO.getFreelancerId() == null ||
                applicationDTO.getCoverLetter() == null || applicationDTO.getResume() == null) {
            return false; // Invalid application data
        }

        // Check if the job exists
        Optional<Job> jobOptional = jobRepository.findById(applicationDTO.getJobId());
        if (!jobOptional.isPresent()) {
            return false; // Job does not exist
        }

        // Check if the freelancer exists
        Optional<Freelancer> freelancerOptional = freelancerRepository.findById(applicationDTO.getFreelancerId());
        if (!freelancerOptional.isPresent()) {
            return false; // Freelancer does not exist
        }

        // Create and save the JobApplication entity
        JobApplication jobApplication = new JobApplication();
        jobApplication.setJob(jobOptional.get());
        jobApplication.setFreelancer(freelancerOptional.get());
        jobApplication.setCoverLetter(applicationDTO.getCoverLetter());
        jobApplication.setProposedBudget(String.valueOf(applicationDTO.getProposedBudget()));
        jobApplication.setStatus("Submitted"); // Or any initial status you define

        try {
            jobApplicationRepository.save(jobApplication);
            return true; // Application successfully saved
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Error while saving application
        }
    }
}
