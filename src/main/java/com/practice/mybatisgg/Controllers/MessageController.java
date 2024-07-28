package com.practice.mybatisgg.Controllers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.practice.mybatisgg.Models.QuestRuleUpdateMessage;

@RestController
@RequestMapping("/mq")
public class MessageController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final String EXCHANGE_NAME = "QuestRuleExchange";
    private static final String ROUTING_KEY = "QuestRuleRoutingKey";

    @PostMapping("/send")
    public void sendMessage(@RequestBody QuestRuleUpdateMessage message) {
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, message);
    }
}

