package com.freelax.back_end.Controllers;

import com.freelax.back_end.Entity.Badge;
import com.freelax.back_end.Services.BadgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/badges")
public class BadgeController {

    @Autowired
    private BadgeService badgeService;

    @PostMapping
    public Badge createBadge(@RequestBody Badge badge) {
        return badgeService.createBadge(badge);
    }

    @GetMapping
    public List<Badge> getAllBadges() {
        return badgeService.getAllBadges();
    }

    @PostMapping("/{freelancerId}/assign")
    public void assignBadgeToFreelancer(@PathVariable Long freelancerId, @RequestBody Badge badge) {
        badgeService.assignBadgeToFreelancer(freelancerId, badge);
    }

    @GetMapping("/freelancers/{freelancerId}")
    public List<Badge> getFreelancerBadges(@PathVariable Long freelancerId) {
        return badgeService.getFreelancerBadges(freelancerId);
    }
}
