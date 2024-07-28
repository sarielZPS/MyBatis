package com.practice.mybatisgg.Services;

import lombok.Getter;
import lombok.Setter;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestRuleMessageService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final String EXCHANGE_NAME = "QuestRuleExchange";
    private static final String ROUTING_KEY = "QuestRule.update";

    public void sendQuestRuleUpdateMessage(String userId, int status) {
        QuestRuleUpdateMessage message = new QuestRuleUpdateMessage(userId, status);
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, message);
    }
}

@Getter
@Setter
class QuestRuleUpdateMessage {
    private String userId;
    private int status;

    public QuestRuleUpdateMessage(String userId, int status) {
        this.userId = userId;
        this.status = status;
    }

    // getters and setters
}

