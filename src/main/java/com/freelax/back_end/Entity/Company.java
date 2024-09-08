package com.freelax.back_end.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Long companyId;

    private String username; // Use username as the primary identifier
    private String email;
    private String password;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Job> jobs;
}
