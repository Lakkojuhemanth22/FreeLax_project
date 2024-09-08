package com.freelax.back_end.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private Boolean isRead = false;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "freelancer_id", referencedColumnName = "user_id")
    private Freelancer freelancer;

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "company_id")
    private Company company;

    @Column
    private String criteria;  // To store job alert criteria

    @Column
    private String alertType;  // To specify type of alert (e.g., 'job_alert')
}
