package com.freelax.back_end.Controllers;

import com.freelax.back_end.Entity.Team;
import com.freelax.back_end.Entity.TeamMember;
import com.freelax.back_end.Entity.TeamSkill;
import com.freelax.back_end.Services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    // Create a new team
    @PostMapping("/createTeam")
    public ResponseEntity<Team> createTeam(@RequestBody Team team) {
        Team createdTeam = teamService.createTeam(team);
        return ResponseEntity.ok(createdTeam);
    }

    // Add a member to a team
    @PostMapping("/{teamId}/members")
    public ResponseEntity<TeamMember> addTeamMember(@PathVariable Long teamId, @RequestBody TeamMember teamMember) {
        TeamMember createdTeamMember = teamService.addTeamMember(teamId, teamMember);
        return ResponseEntity.ok(createdTeamMember);
    }

    // Add skills to a team
    @PostMapping("/{teamId}/skills")
    public ResponseEntity<TeamSkill> addTeamSkill(@PathVariable Long teamId, @RequestBody TeamSkill teamSkill) {
        TeamSkill createdTeamSkill = teamService.addTeamSkill(teamId, teamSkill);
        return ResponseEntity.ok(createdTeamSkill);
    }

    // Get a team by ID
    @GetMapping("/{teamId}")
    public ResponseEntity<Team> getTeamById(@PathVariable Long teamId) {
        Optional<Team> team = teamService.getTeamById(teamId);
        return team.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all members of a team
    @GetMapping("/{teamId}/members")
    public ResponseEntity<List<TeamMember>> getTeamMembers(@PathVariable Long teamId) {
        List<TeamMember> teamMembers = teamService.getTeamMembers(teamId);
        return ResponseEntity.ok(teamMembers);
    }

    // Get all skills of a team
    @GetMapping("/{teamId}/skills")
    public ResponseEntity<List<TeamSkill>> getTeamSkills(@PathVariable Long teamId) {
        List<TeamSkill> teamSkills = teamService.getTeamSkills(teamId);
        return ResponseEntity.ok(teamSkills);
    }

    // Delete a team
    @DeleteMapping("/{teamId}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long teamId) {
        teamService.deleteTeam(teamId);
        return ResponseEntity.ok().build();
    }
}

