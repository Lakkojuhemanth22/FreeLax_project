package com.freelax.back_end.Repository;

import com.freelax.back_end.Entity.TeamSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamSkillRepository extends JpaRepository<TeamSkill, Long> {
    List<TeamSkill> findByTeam_TeamId(Long teamId);
}
