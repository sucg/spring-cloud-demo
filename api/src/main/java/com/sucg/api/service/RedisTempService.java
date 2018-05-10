package com.sucg.api.service;

import com.sucg.api.model.RedisModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class RedisTempService {

    @Autowired
    @Qualifier(value = "sessionRedisTemplate")
    private RedisTemplate sessionRedisTemplate;

    @Autowired
    @Qualifier(value = "storageRedisTemplate")
    private RedisTemplate storageRedisTemplate;

    private RedisModel clear(String id) {
        RedisModel redisModel = new RedisModel();
        redisModel.setId(id);
        redisModel.setAge(1);
        List<String> list = new ArrayList<>();
        list.add(id);
        redisModel.setList(list);
        sessionRedisTemplate.delete(id);
        return redisModel;
    }

    public RedisModel insert(String id){
        return clear(id);
    }


    public RedisModel update(String id){
        return clear(id);
    }


    public RedisModel query(String id){
        RedisModel redisModel = (RedisModel) sessionRedisTemplate.opsForValue().get(id);
        if (null ==redisModel) {
            redisModel = new RedisModel();
            redisModel.setId(id);
            redisModel.setAge(1);
            List<String> list = new ArrayList<>();
            list.add(id);
            redisModel.setList(list);
            sessionRedisTemplate.opsForValue().set(id, redisModel, 10, TimeUnit.SECONDS);
        }
        return redisModel;
    }

    public RedisModel query2(String id){
        RedisModel redisModel = (RedisModel) storageRedisTemplate.opsForValue().get(id);
        if (null ==redisModel) {
            redisModel = new RedisModel();
            redisModel.setId(id);
            redisModel.setAge(1);
            List<String> list = new ArrayList<>();
            list.add(id);
            redisModel.setList(list);
            storageRedisTemplate.opsForValue().set(id, redisModel, 10, TimeUnit.SECONDS);
        }
        return redisModel;
    }


    public RedisModel delete(String id){
       return clear(id);
    }

    public RedisModel delete2(String id){
        RedisModel redisModel = new RedisModel();
        redisModel.setId(id);
        redisModel.setAge(1);
        List<String> list = new ArrayList<>();
        list.add(id);
        redisModel.setList(list);
        storageRedisTemplate.delete(id);
        return redisModel;
    }



}
