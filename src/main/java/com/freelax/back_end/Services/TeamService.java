package com.freelax.back_end.Services;


import com.freelax.back_end.Entity.Team;
import com.freelax.back_end.Entity.TeamMember;
import com.freelax.back_end.Entity.TeamSkill;
import com.freelax.back_end.Repository.TeamRepository;
import com.freelax.back_end.Repository.TeamMemberRepository;
import com.freelax.back_end.Repository.TeamSkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TeamMemberRepository teamMemberRepository;

    @Autowired
    private TeamSkillRepository teamSkillRepository;

    // Create a new team
    public Team createTeam(Team team) {
        return teamRepository.save(team);
    }

    // Add a member to a team
    public TeamMember addTeamMember(Long teamId, TeamMember teamMember) {
        Optional<Team> team = teamRepository.findById(teamId);
        if (team.isPresent()) {
            teamMember.setTeam(team.get());
            return teamMemberRepository.save(teamMember);
        }
        return null; // Handle not found case appropriately
    }

    // Add skills to a team
    public TeamSkill addTeamSkill(Long teamId, TeamSkill teamSkill) {
        Optional<Team> team = teamRepository.findById(teamId);
        if (team.isPresent()) {
            teamSkill.setTeam(team.get());
            return teamSkillRepository.save(teamSkill);
        }
        return null; // Handle not found case appropriately
    }

    // Get team by ID
    public Optional<Team> getTeamById(Long teamId) {
        return teamRepository.findById(teamId);
    }

    // Get all members of a team
    public List<TeamMember> getTeamMembers(Long teamId) {
        return teamMemberRepository.findByTeam_TeamId(teamId);
    }

    // Get all skills of a team
    public List<TeamSkill> getTeamSkills(Long teamId) {
        return teamSkillRepository.findByTeam_TeamId(teamId);
    }

    // Delete a team by ID
    public void deleteTeam(Long teamId) {
        teamRepository.deleteById(teamId);
    }
}

