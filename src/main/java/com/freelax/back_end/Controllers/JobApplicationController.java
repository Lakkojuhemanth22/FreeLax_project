package com.freelax.back_end.Controllers;

import com.freelax.back_end.DTO.JobApplicationDTO;
import com.freelax.back_end.Entity.JobApplication;
import com.freelax.back_end.Repository.FreelancerRepository;
import com.freelax.back_end.Repository.JobApplicationRepository;
import com.freelax.back_end.Repository.JobRepository;
import com.freelax.back_end.Services.JobApplicationService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job-applications")
public class JobApplicationController {

    @Autowired
    JobApplicationRepository jobApplicationRepository;

    @Setter
    @Getter
    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private FreelancerRepository freelancerRepository;

    @Autowired
    private JobApplicationService jobApplicationService;

    // Create a new job application
    @PostMapping("/")
    public ResponseEntity<JobApplication> createJobApplication(@RequestBody JobApplication jobApplication) {
        JobApplication savedApplication = jobApplicationRepository.save(jobApplication);
        return ResponseEntity.ok(savedApplication);
    }

    // Get all job applications
    @GetMapping("/")
    public List<JobApplication> getAllJobApplications() {
        return jobApplicationRepository.findAll();
    }

    // Get a specific job application by ID
    @GetMapping("/{applicationId}")
    public ResponseEntity<JobApplication> getJobApplicationById(@PathVariable Long applicationId) {
        return jobApplicationRepository.findById(applicationId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/apply")
    public ResponseEntity<String> applyForJob(@RequestBody JobApplicationDTO applicationDTO) {
        boolean success = jobApplicationService.applyForJob(applicationDTO);
        if (success) {
            return ResponseEntity.ok("Application submitted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to submit application.");
        }
    }

    // Get job applications by job ID
    @GetMapping("/job/{jobId}")
    public List<JobApplication> getJobApplicationsByJobId(@PathVariable Long jobId) {
        return jobApplicationRepository.findByJob_JobId(jobId);
    }

    // Get job applications by freelancer ID
    @GetMapping("/freelancer/{freelancerId}")
    public List<JobApplication> getJobApplicationsByFreelancerId(@PathVariable Long freelancerId) {
        return jobApplicationRepository.findByFreelancer_UserId(freelancerId);
    }

    // Update a job application
    @PutMapping("/{applicationId}")
    public ResponseEntity<JobApplication> updateJobApplication(@PathVariable Long applicationId, @RequestBody JobApplication jobApplicationDetails) {
        return jobApplicationRepository.findById(applicationId)
                .map(jobApplication -> {
                    jobApplication.setCoverLetter(jobApplicationDetails.getCoverLetter());
                    jobApplication.setProposedBudget(jobApplicationDetails.getProposedBudget());
                    jobApplication.setStatus(jobApplicationDetails.getStatus());
                    JobApplication updatedApplication = jobApplicationRepository.save(jobApplication);
                    return ResponseEntity.ok(updatedApplication);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete a job application
    @DeleteMapping("/{applicationId}")
    public ResponseEntity<Object> deleteJobApplication(@PathVariable Long applicationId) {
        return jobApplicationRepository.findById(applicationId)
                .map(jobApplication -> {
                    jobApplicationRepository.delete(jobApplication);
                    return ResponseEntity.noContent().build(); // or ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
