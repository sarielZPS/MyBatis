package com.practice.mybatisgg.Controllers;

import com.practice.mybatisgg.Mapper.AwardMapper;
import com.practice.mybatisgg.Models.Award;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/award")
public class AwardController {
    @Autowired
    private AwardMapper awardMapper;
    @PostMapping
    public void createAward(@RequestBody Award award) {
        if (award.getId() == null || award.getId().isEmpty()) {
            award.setId(UUID.randomUUID().toString());
        }
        awardMapper.insertAward(award);
    }

}
