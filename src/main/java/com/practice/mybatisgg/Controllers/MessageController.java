package com.practice.mybatisgg.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.practice.mybatisgg.Models.QuestRuleUpdateMessage;
import com.practice.mybatisgg.Configs.RabbitMQConfig;

@RestController
@RequestMapping("/mq")
public class MessageController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/send")
    public void sendMessage(@RequestBody QuestRuleUpdateMessage message) throws JsonProcessingException {
        String jsonMessage = objectMapper.writeValueAsString(message);
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, jsonMessage);
    }
}

