package com.freelax.back_end.Repository;

import com.freelax.back_end.Entity.SkillTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillTestRepository extends JpaRepository<SkillTest, Long> {
}
