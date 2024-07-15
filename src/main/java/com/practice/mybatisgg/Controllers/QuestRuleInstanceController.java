package com.practice.mybatisgg.Controllers;

import com.practice.mybatisgg.Mapper.QuestRuleInstanceMapper;
import com.practice.mybatisgg.Models.QuestRuleInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/questRuleInstance")
public class QuestRuleInstanceController {
    @Autowired
    private QuestRuleInstanceMapper questRuleInstanceMapper;

    @PostMapping
    public void createQuestRule(@RequestBody QuestRuleInstance questRuleInstance) {
        if (questRuleInstance.getId() == null || questRuleInstance.getId().isEmpty()) {
            questRuleInstance.setId(UUID.randomUUID().toString());
        }
        questRuleInstanceMapper.insertQuestRuleInstance(questRuleInstance);
    }

}
