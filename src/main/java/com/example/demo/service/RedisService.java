package com.example.demo.service;

import com.example.demo.api.response.WeatherResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    public <T> T get(String key, Class<T> clazz){
        try{
            Object o = redisTemplate.opsForValue().get(key);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(o.toString(), clazz);
        } catch (Exception e) {
            return null;
        }
    }
    public void set(String key, Object value, long ttl){
        try{
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(value);
            redisTemplate.opsForValue().set(key, json, ttl,TimeUnit.SECONDS);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
