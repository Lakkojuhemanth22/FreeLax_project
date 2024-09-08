package com.freelax.back_end.Controllers;

import com.freelax.back_end.Entity.FreelancerTestResult;
import com.freelax.back_end.Services.FreelancerTestResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test-results")
public class FreelancerTestResultController {

    @Autowired
    private FreelancerTestResultService freelancerTestResultService;

    @PostMapping
    public FreelancerTestResult saveTestResult(@RequestBody FreelancerTestResult result) {
        return freelancerTestResultService.saveTestResult(result);
    }

    @GetMapping("/{userId}/{testId}")
    public FreelancerTestResult getTestResult(@PathVariable Long userId, @PathVariable Long testId) {
        return freelancerTestResultService.getTestResult(userId, testId);
    }
}
