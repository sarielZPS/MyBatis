package com.practice.mybatisgg.Mapper;


import com.practice.mybatisgg.Models.QuestRuleInstance;
import org.apache.ibatis.annotations.*;

public interface QuestRuleInstanceMapper {
    @Insert("INSERT INTO quest_rule_instance(id,user_id, quest_rule_id, status, created_date, created_by, updated_date, updated_by) VALUES(#{id},#{userId}, #{questRuleId}, #{status}, NOW(), #{createdBy}, NOW(), #{updatedBy})")
    void insertQuestRuleInstance(QuestRuleInstance questRuleInstance);

    @Update("UPDATE quest_rule_instance SET status = #{status} WHERE user_id = #{userId}")
    void updateQuestRuleInstanceStatus(@Param("id") String userId, @Param("status") int status);
}
