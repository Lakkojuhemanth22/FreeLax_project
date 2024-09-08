package com.freelax.back_end.Controllers;

import com.freelax.back_end.DTO.JobApplicationDTO;
import com.freelax.back_end.Entity.Job;
import com.freelax.back_end.Repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    @Autowired
    private JobRepository jobRepository;

    // Post a new job
    @PostMapping("/post")
    public ResponseEntity<Job> postJob(@RequestBody Job job) {
        Job savedJob = jobRepository.save(job);
        return ResponseEntity.ok(savedJob);
    }


    // Get all jobs by a specific company
    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<Job>> getJobsByCompany(@PathVariable Long companyId) {
        List<Job> jobs = jobRepository.findByCompanyCompanyId(companyId);
        return ResponseEntity.ok(jobs);
    }

    // Get all jobs
    @GetMapping
    public ResponseEntity<List<Job>> getAllJobs() {
        return ResponseEntity.ok(jobRepository.findAll());
    }

    @PostMapping("/apply")
    public ResponseEntity<String> applyForJob(@RequestBody JobApplicationDTO applicationDTO) {
        // Implement application submission logic here
        return ResponseEntity.ok("Application submitted successfully.");
    }

    // Update a job
    @PutMapping("/{jobId}")
    public ResponseEntity<Job> updateJob(@PathVariable Long jobId, @RequestBody Job jobDetails) {
        Job job = jobRepository.findById(jobId).orElseThrow(() -> new RuntimeException("Job not found"));
        job.setJobName(jobDetails.getJobName());
        job.setJobDescription(jobDetails.getJobDescription());
        job.setJobSalary(jobDetails.getJobSalary());
        job.setSkillsRequired(jobDetails.getSkillsRequired());
        Job updatedJob = jobRepository.save(job);
        return ResponseEntity.ok(updatedJob);
    }

    // Delete a job
    @DeleteMapping("/{jobId}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long jobId) {
        Job job = jobRepository.findById(jobId).orElseThrow(() -> new RuntimeException("Job not found"));
        jobRepository.delete(job);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/searchBySkills")
    public ResponseEntity<List<Job>> searchJobsBySkills(@RequestParam List<String> skills) {
        List<Job> jobs = jobRepository.findJobsBySkills(skills);
        return ResponseEntity.ok(jobs);
    }


    @GetMapping("/search")
    public ResponseEntity<List<Job>> searchJobs(
            @RequestParam(required = false) String skill,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String budget) {

        List<Job> jobs = jobRepository.findAll();

        if (skill != null && !skill.isEmpty()) {
            jobs = jobs.stream()
                    .filter(job -> job.getSkillsRequired().contains(skill))
                    .collect(Collectors.toList());
        }

        if (location != null && !location.isEmpty()) {
            // Assuming job location is stored in the job description for simplicity
            jobs = jobs.stream()
                    .filter(job -> job.getJobDescription().contains(location))
                    .collect(Collectors.toList());
        }

        if (budget != null && !budget.isEmpty()) {
            try {
                double budgetValue = Double.parseDouble(budget);
                jobs = jobs.stream()
                        .filter(job -> job.getJobSalary() <= budgetValue) // Assuming you want to filter jobs within the budget
                        .collect(Collectors.toList());
            } catch (NumberFormatException e) {
                // Handle the case where the budget parameter is not a valid number
                return ResponseEntity.badRequest().build();
            }
        }

        return ResponseEntity.ok(jobs);
    }
}
