package com.freelax.back_end.Services;

import com.freelax.back_end.DTO.LoginRequest;
import com.freelax.back_end.Entity.Company;
import com.freelax.back_end.Entity.Freelancer;
import com.freelax.back_end.Repository.CompanyRepository;
import com.freelax.back_end.Repository.FreelancerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    @Autowired
    private FreelancerRepository freelancerRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String authenticate(LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        // Check freelancer
        Freelancer freelancer = freelancerRepository.findByUsername(username);
        if (freelancer != null) {
            if (passwordEncoder.matches(password, freelancer.getPassword())) {
                return jwtUtil.generateToken(freelancer.getUsername());
            }
        }

        // Check company
        Company company = companyRepository.findByUsername(username);
        if (company != null && passwordEncoder.matches(password, company.getPassword())) {
            return jwtUtil.generateToken(company.getUsername());
        }
        throw new RuntimeException("Invalid credentials");
    }
}
