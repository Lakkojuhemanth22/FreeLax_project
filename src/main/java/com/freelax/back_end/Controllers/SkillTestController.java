package com.freelax.back_end.Controllers;

import com.freelax.back_end.Entity.SkillTest;
import com.freelax.back_end.Services.SkillTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skill-tests")
public class SkillTestController {

    @Autowired
    private SkillTestService skillTestService;

    @PostMapping
    public SkillTest createSkillTest(@RequestBody SkillTest skillTest) {
        return skillTestService.saveSkillTest(skillTest);
    }

    @GetMapping("/{id}")
    public SkillTest getSkillTest(@PathVariable Long id) {
        return skillTestService.getSkillTest(id);
    }

    @GetMapping
    public List<SkillTest> getAllSkillTests() {
        return skillTestService.getAllSkillTests();
    }

    @PutMapping("/{id}")
    public SkillTest updateSkillTest(@PathVariable Long id, @RequestBody SkillTest skillTest) {
        return skillTestService.updateSkillTest(id, skillTest);
    }

    @DeleteMapping("/{id}")
    public void deleteSkillTest(@PathVariable Long id) {
        skillTestService.deleteSkillTest(id);
    }
}