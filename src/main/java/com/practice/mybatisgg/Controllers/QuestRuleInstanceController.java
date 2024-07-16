package com.practice.mybatisgg.Controllers;

import com.practice.mybatisgg.Mapper.QuestRuleInstanceMapper;
import com.practice.mybatisgg.Mapper.QuestRuleMapper;
import com.practice.mybatisgg.Models.QuestRule;
import com.practice.mybatisgg.Models.QuestRuleInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/questRuleInstance")
public class QuestRuleInstanceController {
    @Autowired
    private QuestRuleInstanceMapper questRuleInstanceMapper;

    @Autowired
    private QuestRuleMapper questRuleMapper;

    @PostMapping
    public void createQuestRule(@RequestBody QuestRuleInstance questRuleInstance) {
        List<QuestRule> questRules = questRuleMapper.selectByStatus(0);
        if(containsId(questRules,questRuleInstance.getId()))
        {
            if (questRuleInstance.getId() == null || questRuleInstance.getId().isEmpty()) {
                questRuleInstance.setId(UUID.randomUUID().toString());
            }
            questRuleInstanceMapper.insertQuestRuleInstance(questRuleInstance);
        }
        else{
            throw new IllegalArgumentException("只能创建上架的任务");
        }
    }

    private boolean containsId(List<QuestRule> questRules, String id) {
        for (QuestRule questRule : questRules) {
            if(questRule.getId().equals(id))
                return true;
        }
        return false;
    }

    @PutMapping("/{id}")
    public void updateQuestRuleStatus(@PathVariable String id, @RequestParam int status) {
        questRuleMapper.updateQuestRuleStatus(id, status);
    }

}
