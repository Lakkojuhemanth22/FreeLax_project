package com.freelax.back_end.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@IdClass(FreelancerTestResultId.class)
@Table(name = "freelancer_test_result")
public class FreelancerTestResult {

    @Id
    private Long userId;

    @Id
    private Long testId;

    @MapsId("userId")
    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private Freelancer freelancer;

    @MapsId("testId")
    @ManyToOne
    @JoinColumn(name = "test_id", insertable = false, updatable = false)
    private SkillTest skillTest;

    private int score;
    private boolean passed;
    private boolean awardedBadge;
}
