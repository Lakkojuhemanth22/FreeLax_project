package com.freelax.back_end.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@IdClass(FreelancerBadgeId.class)
@Table(name = "freelancer_badge")
public class FreelancerBadge {

    @Id
    @Column(name = "user_id")  // Ensure the column name matches the database schema
    private Long userId;

    @Id
    @Column(name = "badge_id")  // Ensure the column name matches the database schema
    private Long badgeId;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private Freelancer freelancer;

    @ManyToOne
    @JoinColumn(name = "badge_id", insertable = false, updatable = false)
    private Badge badge;

    @Column(name = "awarded_at")  // Ensure column name matches the database schema
    private String issuedDate;
}
