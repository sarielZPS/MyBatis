package com.practice.mybatisgg.Mapper;

import com.practice.mybatisgg.Models.Award;
import org.apache.ibatis.annotations.Insert;

public interface AwardMapper {
    @Insert("INSERT INTO award(id, user_id, award_rule_id, amount, award_type, created_date, created_by, updated_date, updated_by) VALUES(#{id},#{userId}, #{awardRuleId},#{amount}, #{awardType}, NOW(), #{createdBy}, NOW(), #{updatedBy})")
    void insertAward(Award award);
}
