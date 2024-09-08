package com.freelax.back_end.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "badge")
public class Badge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long badgeId;

    private String badgeName;
    private String badgeDescription;

    @ManyToMany(mappedBy = "badges")
    private List<Freelancer> freelancers;
}
