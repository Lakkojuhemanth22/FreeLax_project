package com.freelax.back_end.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Data
@Getter
@Setter
@Table(name = "Job_application")
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationId;

    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;  // Correct field name, as 'Job' refers to the Job entity.

    private String applicationName;

    private String jobName;

    @ManyToOne
    @JoinColumn(name = "freelancer_id", nullable = false)
    private Freelancer freelancer;

    @Lob
    private String coverLetter;

    private String proposedBudget;

    private String status; // Example: Applied, Negotiation, Accepted, Rejected

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
        updatedAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }
}
