package com.freelax.back_end.Controllers;

import com.freelax.back_end.DTO.FreelancerLoginDTO;
import com.freelax.back_end.DTO.LoginRequest;
import com.freelax.back_end.Entity.Company;
import com.freelax.back_end.Entity.Freelancer;
import com.freelax.back_end.Services.CompanyService;
import com.freelax.back_end.Services.FreelancerService;
import com.freelax.back_end.Services.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private FreelancerService freelancerService;

    @Autowired
    private CompanyService companyService;

    @PostMapping("/login/freelancer")
    public ResponseEntity<String> loginFreelancer(@RequestBody FreelancerLoginDTO loginRequest) {
        Freelancer authenticatedFreelancer = freelancerService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
        if (authenticatedFreelancer != null) {
            String jwtToken = jwtUtil.generateToken(authenticatedFreelancer.getUsername());
            return ResponseEntity.ok(jwtToken); // Return JWT if authenticated
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }

    @PostMapping("/login/company")
    public ResponseEntity<String> loginCompany(@RequestBody LoginRequest loginRequest) {
        Company authenticatedCompany = companyService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
        if (authenticatedCompany != null) {
            String jwtToken = jwtUtil.generateToken(authenticatedCompany.getUsername());
            return ResponseEntity.ok(jwtToken); // Return JWT if authenticated
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }
}
