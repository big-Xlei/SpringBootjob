package com.study.redis.redis_springboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class RedisSpringbootApplicationTests {

    @Test
    void contextLoads() {
    }
    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate redisTemplate;


    @Test
    public void testRedis(){
        //数据库操作
        redisTemplate.getConnectionFactory().getConnection().flushDb();
        redisTemplate.opsForValue().set("xiong","lei");
        System.out.println(redisTemplate.opsForValue().get("xiong"));
    }
}
