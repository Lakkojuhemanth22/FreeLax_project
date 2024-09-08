package com.freelax.back_end.Services;

import com.freelax.back_end.Entity.Company;
import com.freelax.back_end.Entity.Job;
import com.freelax.back_end.Repository.CompanyRepository;
import com.freelax.back_end.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Company createProfile(Company company) {
        company.setPassword(passwordEncoder.encode(company.getPassword()));
        return companyRepository.save(company);
    }

    public Job postNewJob(Long companyId, Job job) {
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new ResourceNotFoundException("Company not found"));
        job.setCompany(company);
        company.getJobs().add(job);
        companyRepository.save(company);
        return job;
    }

    public Company getProfile(Long id) {
        return companyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Company not found"));
    }

    public Company getCompanyByUsername(String username) {
        Company company = companyRepository.findByUsername(username);
        if (company == null) {
            throw new ResourceNotFoundException("Company not found with username: " + username);
        }
        return company;
    }


    public Company authenticate(String username, String password) {
        Company company = companyRepository.findByUsername(username);
        if (company != null && passwordEncoder.matches(password, company.getPassword())) {
            return company;
        }
        return null; // Authentication failed
    }

}
