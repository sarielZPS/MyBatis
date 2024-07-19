package com.practice.mybatisgg.Mapper;

import com.practice.mybatisgg.Models.AwardRule;
import org.apache.ibatis.annotations.Insert;

public interface AwardRuleMapper {
    @Insert("INSERT INTO award_rule(id, quest_rule_id, amount, award_type, status, created_date, created_by, updated_date, updated_by) VALUES(#{id},#{questRuleId}, #{amount}, #{awardType}, #{status}, NOW(), #{createdBy}, NOW(), #{updatedBy})")
    void insertAwardRule(AwardRule awardRule);
}
