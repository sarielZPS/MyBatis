package com.practice.mybatisgg.Configs;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_NAME = "QuestRuleQueue";
    public static final String EXCHANGE_NAME = "QuestRuleExchange";
    public static final String ROUTING_KEY = "QuestRule.update";

    @Bean
    public TopicExchange QuestRuleExchange() {
        return new TopicExchange("QuestRuleExchange");
    }

    @Bean
    public Queue QuestRuleQueue() {
        return new Queue("QuestRuleQueue");
    }

    @Bean
    public Binding bindingQuestRuleQueue(Queue QuestRuleQueue, TopicExchange QuestRuleExchange) {
        return BindingBuilder.bind(QuestRuleQueue).to(QuestRuleExchange).with("QuestRule.update");
    }
}

