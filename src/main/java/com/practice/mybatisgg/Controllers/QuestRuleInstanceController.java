package com.practice.mybatisgg.Controllers;

import com.practice.mybatisgg.Mapper.QuestRuleInstanceMapper;
import com.practice.mybatisgg.Mapper.QuestRuleMapper;
import com.practice.mybatisgg.Models.QuestRule;
import com.practice.mybatisgg.Models.QuestRuleInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.HashSet;
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
        HashMap<String,QuestRule> questRules = questRuleMapper.selectByStatus(0);
        if(questRules.containsKey(questRuleInstance.getQuestRuleId()))
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

    @PutMapping("/{id}")
    public void updateQuestRuleStatus(@PathVariable String userId, @RequestParam int status) {
        HashMap<String,QuestRule> questRules = questRuleMapper.selectByStatus(0);
        //if(containsId(questRules,id))
        //{
        //    questRuleInstanceMapper.updateQuestRuleInstanceStatus(id, status);
        //}

    }

}
