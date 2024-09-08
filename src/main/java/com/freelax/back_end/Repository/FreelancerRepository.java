package com.freelax.back_end.Repository;

import com.freelax.back_end.Entity.Freelancer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FreelancerRepository extends JpaRepository<Freelancer, Long> {
    Freelancer findByUsername(String username);
    Freelancer findByUsernameAndPassword(String username, String password);
}
