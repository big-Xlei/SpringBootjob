package com.study.redis.redis_springboot.conntest;

import org.junit.Test;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

public class RedisConntTest {
    @Resource
    RedisTemplate redisTemplate;


    @Test
    public void testRedis(){
        redisTemplate.opsForValue().set("xiong","lei");
        System.out.println(redisTemplate.opsForValue().get("xiong"));
    }
}
