package com.sucg.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sound.midi.Soundbank;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test() {
        String key = "1";
        String value = "1";
        redisTemplate.opsForValue().set(key, value);
        System.out.println(redisTemplate.opsForValue().get(key));
        redisTemplate.opsForValue().set(key, "2");
        System.out.println(redisTemplate.opsForValue().get(key));
        redisTemplate.delete(key);
        System.out.println(redisTemplate.opsForValue().get(key));
    }

}
