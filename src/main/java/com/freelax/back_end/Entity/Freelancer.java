package com.freelax.back_end.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "Freelancer")
public class Freelancer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    private String username;
    private String password;
    private String email;

    @ElementCollection
    private List<String> skills;

    @ManyToMany
    @JoinTable(
            name = "freelancer_badge",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "badge_id")
    )
    private List<Badge> badges;


}
