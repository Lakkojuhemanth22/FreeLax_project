package com.freelax.back_end.Controllers;

import com.freelax.back_end.DTO.CompanyLoginDTO;
import com.freelax.back_end.Entity.Company;
import com.freelax.back_end.Entity.Job;
import com.freelax.back_end.Services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;


    private CompanyLoginDTO loginDTO;

    @PostMapping("/create")
    public Company createProfile(@RequestBody Company company) {
        return companyService.createProfile(company);
    }

    @PostMapping("/{id}/jobs")
    public Job postNewJob(@PathVariable Long id, @RequestBody Job job) {
        return companyService.postNewJob(id, job);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody CompanyLoginDTO loginDTO) {
        this.loginDTO = loginDTO;
        // Authentication handled by Spring Security
        return ResponseEntity.ok("Company logged in successfully");
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<Company> getCompanyProfile(@PathVariable Long id) {
        Company company = companyService.getProfile(id);
        return ResponseEntity.ok(company);
    }

    @GetMapping("/{username}")
    public ResponseEntity<Company> getCompanyProfileByUsername(@PathVariable String username) {
        Company company = companyService.getCompanyByUsername(username);
        return ResponseEntity.ok(company);
    }
}
