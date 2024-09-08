package com.freelax.back_end.Services;

import com.freelax.back_end.Entity.SkillTest;
import com.freelax.back_end.Repository.SkillTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkillTestService {

    @Autowired
    private SkillTestRepository skillTestRepository;

    public SkillTest saveSkillTest(SkillTest skillTest) {
        return skillTestRepository.save(skillTest);
    }

    public SkillTest getSkillTest(Long id) {
        return skillTestRepository.findById(id).orElse(null);
    }

    public List<SkillTest> getAllSkillTests() {
        return skillTestRepository.findAll();
    }

    public SkillTest updateSkillTest(Long id, SkillTest updatedSkillTest) {
        Optional<SkillTest> optionalSkillTest = skillTestRepository.findById(id);
        if (optionalSkillTest.isPresent()) {
            SkillTest existingSkillTest = optionalSkillTest.get();
            // Update fields
            existingSkillTest.setTitle(updatedSkillTest.getTitle());
            existingSkillTest.setDescription(updatedSkillTest.getDescription());
            existingSkillTest.setIsPremium(updatedSkillTest.getIsPremium());
            existingSkillTest.setDifficulty(updatedSkillTest.getDifficulty());
            existingSkillTest.setSolutionLink(updatedSkillTest.getSolutionLink());
            existingSkillTest.setAcceptanceRate(updatedSkillTest.getAcceptanceRate());
            existingSkillTest.setFrequency(updatedSkillTest.getFrequency());
            existingSkillTest.setUrl(updatedSkillTest.getUrl());
            existingSkillTest.setDiscussCount(updatedSkillTest.getDiscussCount());
            existingSkillTest.setAccepted(updatedSkillTest.getAccepted());
            existingSkillTest.setSubmissions(updatedSkillTest.getSubmissions());
            existingSkillTest.setCompanies(updatedSkillTest.getCompanies());
            existingSkillTest.setRelatedTopics(updatedSkillTest.getRelatedTopics());
            existingSkillTest.setLikes(updatedSkillTest.getLikes());
            existingSkillTest.setDislikes(updatedSkillTest.getDislikes());
            existingSkillTest.setRating(updatedSkillTest.getRating());
            existingSkillTest.setAskedByFaang(updatedSkillTest.getAskedByFaang());
            existingSkillTest.setSimilarQuestions(updatedSkillTest.getSimilarQuestions());
            return skillTestRepository.save(existingSkillTest);
        }
        return null;
    }

    public void deleteSkillTest(Long id) {
        skillTestRepository.deleteById(id);
    }
}