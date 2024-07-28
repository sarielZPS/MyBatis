package com.practice.mybatisgg.Models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestRuleUpdateMessage {
    private String userId;
    private int status;

    public QuestRuleUpdateMessage(String userId, int status) {
        this.userId = userId;
        this.status = status;
    }
}
