package com.practice.mybatisgg.Models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class QuestRuleInstance {
    private String id;
    private String userId;
    private String questRuleId;
    private int status;
    private Date createdDate;
    private String createdBy;
    private Date updatedDate;
    private String updatedBy;
}
