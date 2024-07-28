package com.practice.mybatisgg.Controllers;

import com.practice.mybatisgg.Mappers.AwardRuleMapper;
import com.practice.mybatisgg.Models.AwardRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@RestController
@RequestMapping("/awardRule")
public class AwardRuleController {
    @Autowired
    private AwardRuleMapper awardRuleMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String AWARD_RULE_CACHE_KEY_PREFIX = "award_rule_";
    private final Lock localLock = new ReentrantLock();

    @PostMapping
    public void createAwardRule(@RequestBody AwardRule awardRule) {
        if (awardRule.getId() == null || awardRule.getId().isEmpty()) {
            awardRule.setId(UUID.randomUUID().toString());
        }
        awardRuleMapper.insertAwardRule(awardRule);
    }
    /*
    @GetMapping("/{questRuleId}")
    public List<AwardRule> getAwardRuleByQuestRule(@PathVariable String questRuleId) {
        return awardRuleMapper.selectAwardRuleByQuestRule(questRuleId);
    }
     */
    @GetMapping("/{questRuleId}")
    public List<AwardRule> getAwardRuleByQuestRule(@PathVariable String questRuleId) {
        String cacheKey = AWARD_RULE_CACHE_KEY_PREFIX + questRuleId;
        List<AwardRule> awardRules = (List<AwardRule>) redisTemplate.opsForValue().get(cacheKey); // 尝试从缓存中获取数据
        if (awardRules == null) {
            localLock.lock(); // 使用本地锁防止缓存击穿
            try {
                awardRules = (List<AwardRule>) redisTemplate.opsForValue().get(cacheKey); // 再次检查缓存是否有值
                if (awardRules == null) { // 如果缓存中没有，查询数据库
                    awardRules = awardRuleMapper.selectAwardRuleByQuestRule(questRuleId);
                    if (awardRules != null && !awardRules.isEmpty()) {
                        redisTemplate.opsForValue().set(cacheKey, awardRules, 1, TimeUnit.HOURS); // 将结果存入缓存，并设置过期时间
                    } else {
                        redisTemplate.opsForValue().set(cacheKey, List.of(), 5, TimeUnit.MINUTES); // 防止缓存穿透，缓存一个空值
                    }
                }
            } finally {
                localLock.unlock();
            }
        }
        return awardRules;
    }
}
