package com.practice.mybatisgg.Models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class QuestRule {

    private String id;
    private String eventCode;
    private String name;
    private int status;
    private Date createdDate;
    private String createdBy;
    private Date updateDate;
    private String updateBy;

}
