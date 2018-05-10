package com.sucg.api.service;

import com.sucg.api.model.RedisModel;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RedisAutoService {

    private RedisModel generateRedisModel(String id) {
        RedisModel redisModel = new RedisModel();
        List<String> list = new ArrayList<>();
        list.add(id);
        redisModel.setAge(1);
        redisModel.setId(id);
        redisModel.setList(list);
        return  redisModel;
    }

    @CacheEvict(value = "session", key = "55555",cacheManager = "sessionRedisCacheManager")

    public RedisModel insert(String id){
        RedisModel model = generateRedisModel(id);
        return model;
    }

    @CacheEvict(value = "auto")
    public RedisModel update(String id){
        RedisModel model = generateRedisModel(id);
        return model;
    }

    @Cacheable(value = {"session"}, key = "55555", cacheManager = "sessionRedisCacheManager")
    public RedisModel query(String id){
        RedisModel model = generateRedisModel(id);
        return model;
    }

    @CacheEvict(value = "auto")
    public RedisModel delete(String id){
        RedisModel model = generateRedisModel(id);
        return model;
    }


}
