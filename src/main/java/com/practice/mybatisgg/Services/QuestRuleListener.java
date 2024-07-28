package com.practice.mybatisgg.Services;

import com.practice.mybatisgg.Controllers.AwardController;
import com.practice.mybatisgg.Mappers.AwardRuleMapper;
import com.practice.mybatisgg.Mappers.QuestRuleInstanceMapper;
import com.practice.mybatisgg.Mappers.QuestRuleMapper;
import com.practice.mybatisgg.Models.Award;
import com.practice.mybatisgg.Models.AwardRule;
import com.practice.mybatisgg.Models.QuestRule;
import com.practice.mybatisgg.Models.QuestRuleInstance;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class QuestRuleListener {

    @Autowired
    private QuestRuleInstanceMapper questRuleInstanceMapper;

    @Autowired
    private QuestRuleMapper questRuleMapper;

    @Autowired
    private AwardController awardController;

    @Autowired
    private AwardRuleMapper awardRuleMapper;

    @RabbitListener(queues = "QuestRuleQueue")
    public void handleTaskUpdateMessage(QuestRuleUpdateMessage message) {
        String userId = message.getUserId();
        int status = message.getStatus();

        HashMap<String,QuestRule> questRules = questRuleMapper.selectByStatus(0);
        HashMap<String,QuestRuleInstance> questRuleInstances = questRuleInstanceMapper.selectInstanceByUser(userId);
        for(QuestRuleInstance questRuleInstance : questRuleInstances.values()) {
            try {
                System.out.println(questRuleInstance.getUserId());
                System.out.println(questRuleInstance.getQuestRuleId());
                if (questRules.containsKey(questRuleInstance.getQuestRuleId())) {
                    questRuleInstanceMapper.updateQuestRuleInstanceStatus(questRuleInstance.getId(), status);
                    if (status == 2) { // send award
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
