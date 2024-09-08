package com.freelax.back_end.Controllers;

import com.freelax.back_end.DTO.FreelancerLoginDTO;
import com.freelax.back_end.DTO.FreelancerProfileDTO;
import com.freelax.back_end.DTO.JobDTO;
import com.freelax.back_end.Entity.Freelancer;
import com.freelax.back_end.Services.FreelancerService;
import com.freelax.back_end.Services.JobRecommendationService;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/freelancer")
public class FreelancerController {

    @Autowired
    private FreelancerService freelancerService;

    @Autowired
    private JobRecommendationService jobRecommendationService;

    @GetMapping("/profile")
    public ResponseEntity<FreelancerProfileDTO> getFreelancerProfile(Authentication authentication) {
        String username = authentication.getName();
        FreelancerProfileDTO profile = freelancerService.getProfileByUsername(username);
        return ResponseEntity.ok(profile);
    }

    @GetMapping("/jobs/recommendations")
    public ResponseEntity<List<JobDTO>> getJobRecommendations(Authentication authentication) {
        String username = authentication.getName();
        FreelancerProfileDTO freelancer = freelancerService.getProfileByUsername(username);
        List<JobDTO> recommendedJobs = jobRecommendationService.getRecommendations(freelancer.getUserId());
        return ResponseEntity.ok(recommendedJobs);
    }

    @PostMapping("/jobs/apply")
    public ResponseEntity<String> applyForJob(Authentication authentication, @RequestParam Long jobId) {
        String username = authentication.getName();
        freelancerService.applyForJob(username, jobId);
        return ResponseEntity.ok("Job applied successfully");
    }


    @PostMapping("/create")
    public Freelancer createAccount(@RequestBody Freelancer freelancer) {
        return freelancerService.createAccount(freelancer);
    }

    @PutMapping("/update/{id}")
    public Freelancer updateAccount(@PathVariable Long id, @RequestBody Freelancer freelancer) {
        return freelancerService.updateAccount(id, freelancer);
    }

    @PostMapping("/{id}/skills")
    public void postNewSkills(@PathVariable Long id, @RequestBody String skill) {
        freelancerService.postNewSkills(id, skill);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody FreelancerLoginDTO loginDTO) {
        // Authentication handled by Spring Security
        return ResponseEntity.ok("Freelancer logged in successfully");
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<Freelancer> getFreelancerProfile(@PathVariable Long id) {
        Freelancer freelancer = freelancerService.getProfile(id);
        return ResponseEntity.ok(freelancer);
    }

    @GetMapping("/{username}")
    public Freelancer getFreelancerProfile(@PathVariable String username) {
        return freelancerService.getFreelancerByUsername(username);
    }
}

