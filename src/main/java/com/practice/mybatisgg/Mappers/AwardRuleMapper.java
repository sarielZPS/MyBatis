package com.practice.mybatisgg.Mappers;

import com.practice.mybatisgg.Models.AwardRule;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AwardRuleMapper {
    @Insert("INSERT INTO award_rule(id, quest_rule_id, amount, award_type, status, created_date, created_by, updated_date, updated_by) VALUES(#{id},#{questRuleId}, #{amount}, #{awardType}, #{status}, NOW(), #{createdBy}, NOW(), #{updatedBy})")
    void insertAwardRule(AwardRule awardRule);

    @Select("SELECT id,amount, award_type FROM award_rule where quest_rule_id = #{questRuleId}")
    List<AwardRule> selectAwardRuleByQuestRule(String questRuleId);
}
