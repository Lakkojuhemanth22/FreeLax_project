package com.freelax.back_end.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "TeamSkill")
public class TeamSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamSkillId;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    private String skillName;
}
