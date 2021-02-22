package com.study.redis.redis_springboot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

@SpringBootTest
class RedisSpringbootApplicationTests {

    @Test
    void contextLoads() {
    }
    @Resource
    RedisTemplate redisTemplate;


    @Test
    public void testRedis(){
        redisTemplate.opsForValue().set("xiong","lei");
        System.out.println(redisTemplate.opsForValue().get("xiong"));
    }
}
