package com.freelax.back_end.DTO;

import com.freelax.back_end.Entity.Badge;
import com.freelax.back_end.Entity.Freelancer;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

@Data // Automatically generates getters, setters, toString, equals, and hashCode methods
public class FreelancerProfileDTO {

    private Long userId;
    private String username;
    private String email;
    private List<String> skills;
    private List<Badge> badges; // Assuming you have a BadgeDTO class for badges

    // Default constructor
    public FreelancerProfileDTO() {
    }

    // Constructor to initialize fields from a Freelancer entity
    public FreelancerProfileDTO(Freelancer freelancer) {
        this.userId = freelancer.getUserId();
        this.username = freelancer.getUsername();
        this.email = freelancer.getEmail();
        this.skills = freelancer.getSkills(); // Assuming skills are a List<String> in Freelancer
        this.badges = freelancer.getBadges(); // Assuming badges are a List<BadgeDTO> in Freelancer
    }

}
