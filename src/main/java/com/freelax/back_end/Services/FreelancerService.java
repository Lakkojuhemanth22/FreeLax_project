package com.freelax.back_end.Services;

import com.freelax.back_end.DTO.FreelancerProfileDTO;
import com.freelax.back_end.Entity.Freelancer;
import com.freelax.back_end.Entity.Job;
import com.freelax.back_end.Entity.JobApplication;
import com.freelax.back_end.Repository.FreelancerRepository;
import com.freelax.back_end.Repository.JobApplicationRepository;
import com.freelax.back_end.Repository.JobRepository;
import com.freelax.back_end.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FreelancerService {

    @Autowired
    private FreelancerRepository freelancerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    @Autowired
    private JobRepository jobRepository;

    public Freelancer createAccount(Freelancer freelancer) {
        freelancer.setPassword(passwordEncoder.encode(freelancer.getPassword()));
        return freelancerRepository.save(freelancer);
    }

    public Freelancer updateAccount(Long userId, Freelancer updatedFreelancer) {
        Freelancer freelancer = freelancerRepository.findById(userId).orElseThrow();
        freelancer.setEmail(updatedFreelancer.getEmail());
        freelancer.setSkills(updatedFreelancer.getSkills());
        return freelancerRepository.save(freelancer);
    }

    public void postNewSkills(Long userId, String skill) {
        Freelancer freelancer = freelancerRepository.findById(userId).orElseThrow();
        freelancer.getSkills().add(skill);
        freelancerRepository.save(freelancer);
    }

    public Freelancer getProfile(Long id) {
        return freelancerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Freelancer not found"));
    }


    public Freelancer getFreelancerByUsername(String username) {
        Freelancer freelancer = freelancerRepository.findByUsername(username);
        if (freelancer == null) {
            throw new RuntimeException("Freelancer not found");
        }
        return freelancer;
    }

    public Freelancer authenticate(String username, String password) {
        Freelancer freelancer = freelancerRepository.findByUsername(username);
        if (freelancer != null && passwordEncoder.matches(password, freelancer.getPassword())) {
            return freelancer;
        }
        return null; // Authentication failed
    }

    public FreelancerProfileDTO getProfileByUsername(String username) {
        Freelancer freelancer = freelancerRepository.findByUsername(username);
        if (freelancer == null) {
            throw new RuntimeException("Freelancer not found");
        }
        return new FreelancerProfileDTO(freelancer);
    }

    public void applyForJob(String username, Long jobId) {
        Freelancer freelancer = freelancerRepository.findByUsername(username);
        Job job = jobRepository.findById(jobId).orElseThrow(() -> new ResourceNotFoundException("Job not found"));

        JobApplication jobApplication = new JobApplication();
        jobApplication.setFreelancer(freelancer);
        jobApplication.setJob(job);
        jobApplicationRepository.save(jobApplication);
    }
}