package com.practice.mybatisgg.Controllers;

import com.practice.mybatisgg.Mapper.AwardRuleMapper;
import com.practice.mybatisgg.Models.AwardRule;
import com.practice.mybatisgg.Models.QuestRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/awardRule")
public class AwardRuleController {
    @Autowired
    private AwardRuleMapper awardRuleMapper;

    @PostMapping
    public void createAwardRule(@RequestBody AwardRule awardRule) {
        if (awardRule.getId() == null || awardRule.getId().isEmpty()) {
            awardRule.setId(UUID.randomUUID().toString());
        }
        awardRuleMapper.insertAwardRule(awardRule);
    }

    @GetMapping("/{questRuleId}")
    public List<AwardRule> getAwardRuleByQuestRule(@PathVariable String questRuleId) {
        return awardRuleMapper.selectAwardRuleByQuestRule(questRuleId);
    }
}
