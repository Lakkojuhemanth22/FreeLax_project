package com.freelax.back_end.Services;

import com.freelax.back_end.Entity.Company;
import com.freelax.back_end.Entity.Freelancer;
import com.freelax.back_end.Repository.CompanyRepository;
import com.freelax.back_end.Repository.FreelancerRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private FreelancerRepository freelancerRepository;

    @Getter
    @Setter
    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Freelancer freelancer = freelancerRepository.findByUsername(username);
        if (freelancer != null) {
            return org.springframework.security.core.userdetails.User.builder()
                    .username(freelancer.getUsername())
                    .password(freelancer.getPassword())
                    .build();
        }

        throw new UsernameNotFoundException("User not found");
    }

}
