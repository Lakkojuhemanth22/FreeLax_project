package com.freelax.back_end.Controllers;

import com.freelax.back_end.DTO.JobDTO;
import com.freelax.back_end.Services.JobRecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobRecommendationController {

    @Autowired
    private JobRecommendationService jobRecommendationService;

    @GetMapping("/recommendations")
    public ResponseEntity<List<JobDTO>> getJobRecommendations(@RequestParam Long freelancerId) {
        List<JobDTO> recommendations = jobRecommendationService.getRecommendations(freelancerId);
        return ResponseEntity.ok(recommendations);
    }
}
