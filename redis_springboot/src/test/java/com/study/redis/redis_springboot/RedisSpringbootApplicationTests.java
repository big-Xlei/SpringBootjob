package com.study.redis.redis_springboot;

import com.study.redis.redis_springboot.bean.User;
import com.study.redis.redis_springboot.utils.RedisUtil;
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
    private RedisUtil redisUtil;

//    @Test
//    public void testRedis(){
//        redisUtil.set("xiong","lei");
//        redisUtil.set("user",new User("大熊",20));
//        System.out.println(redisUtil.get("user"));
//        System.out.println(redisUtil.get("xiong"));
//    }
}
