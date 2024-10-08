package com.practice.mybatisgg.Models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class QuestRuleUpdateMessage implements Serializable {
    private String userId;
    private int status;

    public QuestRuleUpdateMessage(String userId, int status) {
        this.userId = userId;
        this.status = status;
    }
}
