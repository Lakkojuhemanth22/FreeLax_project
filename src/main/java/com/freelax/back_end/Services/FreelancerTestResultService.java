package com.freelax.back_end.Services;

import com.freelax.back_end.Entity.Badge;
import com.freelax.back_end.Entity.FreelancerTestResult;
import com.freelax.back_end.Entity.FreelancerTestResultId;
import com.freelax.back_end.Repository.FreelancerTestResultRepository;
import com.freelax.back_end.Repository.SkillTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FreelancerTestResultService {

    @Autowired
    private FreelancerTestResultRepository freelancerTestResultRepository;

    @Autowired
    private SkillTestRepository skillTestRepository;

    @Autowired
    private BadgeService badgeService;

    public FreelancerTestResult saveTestResult(FreelancerTestResult result) {
        if (result.getScore() >= 80) { // Example condition
            result.setAwardedBadge(true);
            Badge badge = badgeService.awardBadge(result);
            // You might want to associate this badge with the result or freelancer
        }
        return freelancerTestResultRepository.save(result);
    }

    public FreelancerTestResult getTestResult(Long userId, Long testId) {
        FreelancerTestResultId id = new FreelancerTestResultId(userId, testId);
        return freelancerTestResultRepository.findById(id).orElse(null);
    }
}
