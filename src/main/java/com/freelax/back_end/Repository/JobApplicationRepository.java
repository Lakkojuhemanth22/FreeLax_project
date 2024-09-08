package com.freelax.back_end.Repository;

import com.freelax.back_end.Entity.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {

    List<JobApplication> findByFreelancer_UserId(Long freelancerId);
    // Updated method to match the Job entity's field name
    List<JobApplication> findByJob_JobId(Long jobId);

}


