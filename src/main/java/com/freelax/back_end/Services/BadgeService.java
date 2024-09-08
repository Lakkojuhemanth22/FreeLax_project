package com.freelax.back_end.Services;

import com.freelax.back_end.Entity.Badge;
import com.freelax.back_end.Entity.Freelancer;
import com.freelax.back_end.Entity.FreelancerTestResult;
import com.freelax.back_end.Repository.BadgeRepository;
import com.freelax.back_end.Repository.FreelancerRepository;
import com.freelax.back_end.Repository.FreelancerTestResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BadgeService {

    @Autowired
    private FreelancerRepository freelancerRepository;

    @Autowired
    private BadgeRepository badgeRepository;

    @Autowired
    private FreelancerTestResultRepository freelancerTestResultRepository;

    // Method to award badge based on test results
    public Badge awardBadge(FreelancerTestResult result) {
        if (result.isAwardedBadge()) {
            // For simplicity, create a new badge, or find an existing badge based on your logic
            Badge badge = new Badge();
            badge.setBadgeName("Certified Expert"); // Example badge name
            badge.setBadgeDescription("Awarded for excellent performance in skill tests.");
            return badgeRepository.save(badge);
        }
        return null;
    }

    // Method to get all badges
    public List<Badge> getAllBadges() {
        return badgeRepository.findAll();
    }


    public Badge createBadge(Badge badge) {
        return badgeRepository.save(badge);
    }

    public void assignBadgeToFreelancer(Long freelancerId, Badge badge) {
        Freelancer freelancer = freelancerRepository.findById(freelancerId).orElseThrow(() -> new RuntimeException("Freelancer not found"));
        freelancer.getBadges().add(badge);
        freelancerRepository.save(freelancer);
    }

    public List<Badge> getFreelancerBadges(Long freelancerId) {
        Freelancer freelancer = freelancerRepository.findById(freelancerId).orElseThrow(() -> new RuntimeException("Freelancer not found"));
        return freelancer.getBadges();
    }
}
