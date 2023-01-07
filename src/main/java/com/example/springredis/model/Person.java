package com.example.springredis.model;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;

@Getter
@RedisHash(value = "person", timeToLive = 60)
public class Person {
    @Id
    private String id;
    private String name;
    private Integer age;
    private LocalDateTime createDate;


    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
        this.createDate = LocalDateTime.now();
    }
}
