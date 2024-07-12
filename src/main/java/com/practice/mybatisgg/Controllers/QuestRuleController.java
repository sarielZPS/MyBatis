package com.practice.mybatisgg.Controllers;

import com.practice.mybatisgg.Models.QuestRule;
import com.practice.mybatisgg.Mapper.QuestRuleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/questRule")
public class QuestRuleController {
    @Autowired
    private QuestRuleMapper questRuleMapper;

    @PostMapping
    public void createQuestRule(@RequestBody QuestRule questRule) {
        if (questRule.getId() == null || questRule.getId().isEmpty()) {
            questRule.setId(UUID.randomUUID().toString());
        }
        questRuleMapper.insertQuestRule(questRule);
    }

    @DeleteMapping("/{id}")
    public void deleteQuestRule(@PathVariable String id) {
        questRuleMapper.deleteQuestRule(id);
    }

    @GetMapping("/{id}")
    public QuestRule getQuestRule(@PathVariable String id) {
        return questRuleMapper.selectQuestRule(id);
    }

    @PutMapping("/{id}")
    public void updateQuestRuleStatus(@PathVariable String id, @RequestParam int status) {
        questRuleMapper.updateQuestRuleStatus(id, status);
    }
}
