package com.sucg.api.api;

import com.sucg.api.model.RedisModel;
import com.sucg.api.service.RedisAutoService;
import com.sucg.api.service.RedisTempService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
public class RedisAPI {

    @Autowired
    private RedisAutoService redisAutoService;

    @Autowired
    private RedisTempService redisTempService;

    @RequestMapping("/test")
    public String test()  {
        return "redis test";
    }

    @RequestMapping("/temp/insert")
    public String insert(String id)  {
        RedisModel value = redisTempService.insert(id);
        return "redis insert." + String.format("%s:%s", id, value);
    }

    @RequestMapping("/temp/delete")
    public String delete(String id)  {
        RedisModel value = redisTempService.delete(id);
        return "redis delete." + String.format("%s:%s", id, value);
    }

    @RequestMapping("/temp/delete2")
    public String delete2(String id)  {
        RedisModel value = redisTempService.delete2(id);
        return "redis delete." + String.format("%s:%s", id, value);
    }

    @RequestMapping("/temp/query")
    public String query(String id) {
        RedisModel value = redisTempService.query(id);
        return "redis query." + String.format("%s:%s", id, value);
    }

    @RequestMapping("/temp/query2")
    public String query2(String id) {
        RedisModel value = redisTempService.query2(id);
        return "redis query." + String.format("%s:%s", id, value);
    }

    @RequestMapping("/temp/modify")
    public String modify(String id) {
        RedisModel value = redisTempService.update(id);
        return "redis modify." + String.format("%s:%s", id, value);
    }

    @RequestMapping("/auto/insert")
    public String insert_auto(String id)  {
        RedisModel value = redisAutoService.insert(id);
        return "redis insert." + String.format("%s:%s", id, value);
    }

    @RequestMapping("/auto/delete")
    public String delete_auto(String id)  {
        RedisModel value = redisAutoService.delete(id);
        return "redis delete." + String.format("%s:%s", id, value);
    }

    @RequestMapping("/auto/query")
    public String query_auto(String id) {
        RedisModel value = redisAutoService.query(id);
        return "redis query." + String.format("%s:%s", id, value);
    }

    @RequestMapping("/auto/modify")
    public String modify_auto(String id) {
        RedisModel value = redisAutoService.update(id);
        return "redis modify." + String.format("%s:%s", id, value);
    }
}
