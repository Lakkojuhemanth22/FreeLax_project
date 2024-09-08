package com.freelax.back_end.Services;

import com.freelax.back_end.DTO.JobDTO;
import com.freelax.back_end.Entity.Freelancer;
import com.freelax.back_end.Entity.Job;
import com.freelax.back_end.Repository.FreelancerRepository;
import com.freelax.back_end.Repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobRecommendationService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private FreelancerRepository freelancerRepository;

    public List<JobDTO> getRecommendations(Long freelancerId) {
        // Fetch freelancer's skills and preferences
        Freelancer freelancer = freelancerRepository.findById(freelancerId)
                .orElseThrow(() -> new RuntimeException("Freelancer not found"));
        List<String> skills = freelancer.getSkills(); // Assume skills are stored as a List of Strings

        // Fetch jobs matching the freelancer's skills
        List<Job> jobs = jobRepository.findJobsBySkills(skills);

        // Convert jobs to DTOs
        return jobs.stream().map(this::convertToDTO).collect(Collectors.toList());
    }



    private JobDTO convertToDTO(Job job) {
        JobDTO dto = new JobDTO();
        dto.setId(job.getJobId()); // Ensure you're using the correct field name
        dto.setJobName(job.getJobName()); // Or any other appropriate field
        dto.setDescription(job.getJobDescription());
        dto.setSkillsRequired(Collections.singletonList(String.join(", ", job.getSkillsRequired()))); // Adjust if skills are stored differently
        dto.setLocation(job.getLocation());
        dto.setPostedDate(job.getPostedDate());
        dto.setEmploymentType(job.getEmploymentType());
        dto.setJobCategory(job.getJobCategory());
        dto.setSalary(job.getJobSalary());
        return dto;
    }

}

