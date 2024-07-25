package com.practice.mybatisgg.Controllers;

import com.practice.mybatisgg.Mapper.AwardRuleMapper;
import com.practice.mybatisgg.Mapper.QuestRuleInstanceMapper;
import com.practice.mybatisgg.Mapper.QuestRuleMapper;
import com.practice.mybatisgg.Models.Award;
import com.practice.mybatisgg.Models.AwardRule;
import com.practice.mybatisgg.Models.QuestRule;
import com.practice.mybatisgg.Models.QuestRuleInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/questRuleInstance")
public class QuestRuleInstanceController {
    @Autowired
    private QuestRuleInstanceMapper questRuleInstanceMapper;

    @Autowired
    private QuestRuleMapper questRuleMapper;

    @Autowired
    private AwardController awardController;
    @Autowired
    private AwardRuleMapper awardRuleMapper;

    @PostMapping
    public void createQuestRule(@RequestBody QuestRuleInstance questRuleInstance) {
        HashMap<String,QuestRule> questRules = questRuleMapper.selectByStatus(0);
        try{
            if(questRules.containsKey(questRuleInstance.getQuestRuleId())) {
                if (questRuleInstance.getId() == null || questRuleInstance.getId().isEmpty()) {
                    questRuleInstance.setId(UUID.randomUUID().toString());
                }
                questRuleInstanceMapper.insertQuestRuleInstance(questRuleInstance);
            }
            else{
                throw new IllegalArgumentException("只能创建上架的任务");
            }
        }catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }

    }

    @PutMapping("/{userId}")
    public void updateQuestRuleStatus(@PathVariable("userId") String userId, @RequestParam int status) {
        HashMap<String,QuestRule> questRules = questRuleMapper.selectByStatus(0);
        HashMap<String,QuestRuleInstance> questRuleInstances = questRuleInstanceMapper.selectInstanceByUser(userId);
        for(QuestRuleInstance questRuleInstance : questRuleInstances.values()) {
            try {
                System.out.println(questRuleInstance.getUserId());
                System.out.println(questRuleInstance.getQuestRuleId());
                if (questRules.containsKey(questRuleInstance.getQuestRuleId())) {
                    questRuleInstanceMapper.updateQuestRuleInstanceStatus(questRuleInstance.getId(), status);
                    if (status == 2) {
                        List<AwardRule> awardRules = awardRuleMapper.selectAwardRuleByQuestRule(questRuleInstance.getQuestRuleId());
                        for (AwardRule awardRule : awardRules) {
                            Award award = getAward(questRuleInstance, awardRule);
                            awardController.createAward(award);
                        }
                    }
                } else {
                    throw new IllegalArgumentException("只能更新上架任务的状态");
                }
            } catch (IllegalArgumentException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

    private static Award getAward(QuestRuleInstance questRuleInstance, AwardRule awardRule) {
        Award award = new Award();
        award.setUserId(questRuleInstance.getUserId());
        award.setAwardRuleId(awardRule.getId());
        award.setAmount(awardRule.getAmount());
        award.setAwardType(awardRule.getAwardType());
        award.setCreatedDate(new Date());
        award.setUpdatedDate(new Date());
        award.setCreatedBy("Automatic");
        award.setUpdatedBy("Automatic");
        return award;
    }

}
