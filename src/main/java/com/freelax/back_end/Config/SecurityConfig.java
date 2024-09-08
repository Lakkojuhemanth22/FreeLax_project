package com.freelax.back_end.Config;

import com.freelax.back_end.Services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF for simplicity (consider enabling it in production)
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/api/jobs/recommendations","/api/auth","/api/job-applications/apply","/api/auth/login/freelancer","/api/auth/login/company","/api/companies/{username}","/api/freelancers/{username}","/freelancer/create", "/freelancer/update/{id}", "/freelancer/{id}/skills", "/freelancer/login","/freelancer/profile/{id}",
                                        "/company/create", "/company/{id}/jobs", "/company/login", "/company/profile/{id}",
                                        "/api/jobs/post", "/api/jobs/company/{companyId}", "/api/jobs", "/api/jobs/{jobId}", "/api/jobs/{jobId}", "/api/jobs/search",
                                        "/api/job-applications/", "/api/job-applications/", "/api/job-applications/{applicationId}", "/api/job-applications/job/{jobId}", "/api/job-applications/freelancer/{freelancerId}",
                                        "/api/job-applications/{applicationId}", "/api/job-applications/{applicationId}",
                                        "/api/teams/createTeam","/api/teams/{teamId}/members","/api/teams/{teamId}/skills","/api/teams/{teamId}","/api/teams/{teamId}/members", "/api/teams/{teamId}/skills","/api/teams/{teamId}",
                                        "/api/notifications/freelancer/{freelancerId}", "/api/notifications/company/{companyId}","/api/notifications/freelancer/{freelancerId}","/api/notifications/company/{companyId}","/api/notifications/{notificationId}/read",
                                        "/api/skill-tests","/api/badges","/api/badges/{userId}/{testId}","/api/badges/{freelancerId}/assign","/api/badges/freelancers/{freelancerId}",
                                        "/api/test-results","/api/test-results/{userId}/{testId}","/api/jobs/searchBySkills",
                                        "/api/notifications/freelancer/{freelancerId}/job-alert","/api/notifications/freelancer/{freelancerId}/job-alerts").permitAll()
                                .anyRequest().authenticated()
                )
                .logout(LogoutConfigurer::permitAll);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return customUserDetailsService;
    }
}
