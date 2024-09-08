package com.freelax.back_end.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "skill_test")
public class SkillTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long testId;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Boolean isPremium;

    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    private String solutionLink;

    private Double acceptanceRate;

    private Integer frequency;

    private String url;

    private Integer discussCount;

    private Integer accepted;

    private Integer submissions;

    @Column(columnDefinition = "TEXT")
    private String companies;

    @Column(columnDefinition = "TEXT")
    private String relatedTopics;

    private Integer likes;

    private Integer dislikes;

    private Double rating;

    private Boolean askedByFaang;

    @Column(columnDefinition = "TEXT")
    private String similarQuestions;

    // Enum for difficulty levels
    public enum Difficulty {
        EASY, MEDIUM, HARD
    }
}

