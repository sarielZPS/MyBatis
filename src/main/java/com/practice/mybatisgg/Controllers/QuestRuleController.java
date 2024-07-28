package com.practice.mybatisgg.Controllers;

import com.practice.mybatisgg.Models.QuestRule;
import com.practice.mybatisgg.Mappers.QuestRuleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@RestController
@RequestMapping("/questRule")
public class QuestRuleController {
    @Autowired
    private QuestRuleMapper questRuleMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String QUEST_RULE_CACHE_KEY_PREFIX = "quest_rule_list_";
    private final Lock localLock = new ReentrantLock();

    @PostMapping
    public void createQuestRule(@RequestBody QuestRule questRule) {
        if (questRule.getId() == null || questRule.getId().isEmpty()) {
            questRule.setId(UUID.randomUUID().toString());
        }
        questRuleMapper.insertQuestRule(questRule);
    }

    @DeleteMapping("/{id}")
    public void deleteQuestRule(@PathVariable String id) {
        questRuleMapper.deleteQuestRule(id);
    }

    @GetMapping("/{id}")
    public QuestRule getQuestRule(@PathVariable String id) {
        return questRuleMapper.selectQuestRule(id);
    }

    @PutMapping("/{id}")
    public void updateQuestRuleStatus(@PathVariable String id, @RequestParam int status) {
        questRuleMapper.updateQuestRuleStatus(id, status);
    }
    /*
    @GetMapping("/GetNewQuestRules")
    public HashMap<String,QuestRule> getShelvedQuestRules() {
        return questRuleMapper.selectByStatus(0);
    }
    */
    @GetMapping("/GetNewQuestRules")
    public HashMap<String, QuestRule> getShelvedQuestRules() {
        String cacheKey = QUEST_RULE_CACHE_KEY_PREFIX + "shelved";
        HashMap<String, QuestRule> questRules = (HashMap<String, QuestRule>) redisTemplate.opsForValue().get(cacheKey);
        if (questRules == null) {
            localLock.lock(); // 使用本地锁防止缓存击穿
            try {
                questRules = (HashMap<String, QuestRule>) redisTemplate.opsForValue().get(cacheKey); // 再次检查缓存是否有值
                if (questRules == null) { // 如果缓存中没有，查询数据库
                    questRules = questRuleMapper.selectByStatus(0);
                    if (questRules != null && !questRules.isEmpty()) {
                        redisTemplate.opsForValue().set(cacheKey, questRules, 1, TimeUnit.HOURS); // 将任务列表存入缓存，并设置过期时间
                    } else {
                        redisTemplate.opsForValue().set(cacheKey, new HashMap<>(), 5, TimeUnit.MINUTES); // 防止缓存穿透，缓存一个空值
                    }
                }
            } finally {
                localLock.unlock();
            }
        }
        return questRules;
    }
}
