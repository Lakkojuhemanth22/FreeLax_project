package com.freelax.back_end.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "TeamMember")
public class TeamMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamMemberId;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "freelancer_id")
    private Freelancer freelancer;

    private String role;
}