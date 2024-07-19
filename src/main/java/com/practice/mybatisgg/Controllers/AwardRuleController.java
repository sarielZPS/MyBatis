package com.practice.mybatisgg.Controllers;

import com.practice.mybatisgg.Mapper.AwardRuleMapper;
import com.practice.mybatisgg.Models.AwardRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/awardRule")
public class AwardRuleController {
    @Autowired
    private AwardRuleMapper awardRuleMapper;

    @PostMapping
    public void createQuestRule(@RequestBody AwardRule awardRule) {
        if (awardRule.getId() == null || awardRule.getId().isEmpty()) {
            awardRule.setId(UUID.randomUUID().toString());
        }
        awardRuleMapper.insertAwardRule(awardRule);
    }
}
