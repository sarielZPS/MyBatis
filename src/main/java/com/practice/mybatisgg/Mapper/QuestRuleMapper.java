package com.practice.mybatisgg.Mapper;

import com.practice.mybatisgg.Models.QuestRule;
import org.apache.ibatis.annotations.*;

import java.util.HashMap;
import java.util.List;

public interface QuestRuleMapper {
    @Insert("INSERT INTO quest_rule(id, event_code, name, status, created_date, created_by, updated_date, updated_by) VALUES(#{id}, #{eventCode}, #{name}, #{status}, NOW(), #{createdBy}, NOW(), #{updatedBy})")
    void insertQuestRule(QuestRule questRule);

    @Delete("DELETE FROM quest_rule WHERE id = #{id}")
    void deleteQuestRule(@Param("id") String id);

    @Select("SELECT id, event_code, name, status FROM quest_rule WHERE id = #{id}")
    QuestRule selectQuestRule(@Param("id") String id);

    @Update("UPDATE quest_rule SET status = #{status} WHERE id = #{id}")
    void updateQuestRuleStatus(@Param("id") String id, @Param("status") int status);

    @Select("SELECT * from quest_rule where status = #{status}")
    @MapKey("id")
    HashMap<String,QuestRule> selectByStatus(@Param("status") int status);
}
