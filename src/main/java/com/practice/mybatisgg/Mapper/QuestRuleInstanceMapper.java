package com.practice.mybatisgg.Mapper;


import com.practice.mybatisgg.Models.QuestRuleInstance;
import org.apache.ibatis.annotations.*;

public interface QuestRuleInstanceMapper {
    @Insert("INSERT INTO quest_rule_instance(id,user_id, quest_rule_id, status, created_date, created_by, updated_date, updated_by) VALUES(#{id},#{userId}, #{requestRuleId}, #{status}, NOW(), #{createdBy}, NOW(), #{updateBy})")
    void insertQuestRuleInstance(QuestRuleInstance questRuleInstance);
}
