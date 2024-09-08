package com.freelax.back_end.Repository;

import com.freelax.back_end.Entity.FreelancerTestResult;
import com.freelax.back_end.Entity.FreelancerTestResultId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FreelancerTestResultRepository extends JpaRepository<FreelancerTestResult, FreelancerTestResultId> {

}