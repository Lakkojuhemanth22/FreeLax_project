package com.freelax.back_end.Repository;

import com.freelax.back_end.Entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByCompanyCompanyId(Long companyId);

    List<Job> findBySkillsRequiredIn(List<String> skillsRequired);

    @Query("SELECT j FROM Job j JOIN j.skillsRequired s WHERE s IN :skills")
    List<Job> findJobsBySkills(@Param("skills") List<String> skills);


}
