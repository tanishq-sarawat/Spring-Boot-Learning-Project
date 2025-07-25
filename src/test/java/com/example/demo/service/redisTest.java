package com.example.demo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
@SpringBootTest
@Component
public class redisTest {

    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    void test(){
        redisTemplate.opsForValue().set("email", "email@gmail.com");
        Object salery = redisTemplate.opsForValue().get("salery");
        int a =1;
    }

}
