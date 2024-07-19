package com.practice.mybatisgg.Models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AwardRule {
    private String id;
    private String questRuleId;
    private float amount;
    private int awardType;
    private int status;
    private Date createdDate;
    private String createdBy;
    private Date updatedDate;
    private String updatedBy;
}
