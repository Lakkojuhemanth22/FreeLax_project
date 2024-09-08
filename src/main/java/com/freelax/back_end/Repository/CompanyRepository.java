package com.freelax.back_end.Repository;

import com.freelax.back_end.Entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company findByUsername(String username);

    Optional<Company> findByUsernameAndPassword(String username, String password);
}
