package com.sucg.api.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RedisModel implements Serializable {
    private String id;
    private Integer age;
    private List<String> list = new ArrayList<>();

    public RedisModel() {
    }

    public RedisModel(String id, Integer age, List<String> list) {
        this.id = id;
        this.age = age;
        this.list = list;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "RedisModel{" +
                "id='" + id + '\'' +
                ", age=" + age +
                ", list=" + list +
                '}';
    }
}
