package com.practice.mybatisgg.Models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Award {
    private String id;
    private String userId;
    private String awardRuleId;
    private float amount;
    private int awardType;
    private Date createdDate;
    private String createdBy;
    private Date updatedDate;
    private String updatedBy;
}
