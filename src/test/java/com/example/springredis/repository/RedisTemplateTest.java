package com.example.springredis.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class RedisTemplateTest {
    @Autowired
    StringRedisTemplate redisTemplate;

    @BeforeEach
    public void clear() {
        redisTemplate.getConnectionFactory().getConnection().flushAll();
    }

    @Test
    @DisplayName("String Test")
    public void stringTest() throws Exception {
        final String key = "myKey";
        final ValueOperations<String, String> stringStringValueOperations = redisTemplate.opsForValue();
        stringStringValueOperations.set(key, "1"); // redis Set operation
        final String result =
                stringStringValueOperations.get(key);

        System.out.println("result : " + result);

        stringStringValueOperations.increment(key); // redis incr operation
        final String result2 = stringStringValueOperations.get(key);
        System.out.println("result2 : " + result2);
    }

    @Test
    @DisplayName("List Test")
    public void listTest() throws Exception {
        final String key = "myKey";
        final ListOperations<String, String> stringStringListOperations = redisTemplate.opsForList();

        stringStringListOperations.rightPush(key, "H");
        stringStringListOperations.rightPush(key, "E");
        stringStringListOperations.rightPush(key, "L");
        stringStringListOperations.rightPush(key, "L");

        stringStringListOperations.rightPushAll(key, "O", "?", "!");

        final String ch1 = stringStringListOperations.index(key, 1);
        System.out.println("ch1 = " + ch1);

        final Long size = stringStringListOperations.size(key);
        System.out.println("size = " + size);

        final List<String> range = stringStringListOperations.range(key, 0, 7);
        System.out.println("Arrays.toString(range.toArray()) = " + Arrays.toString(range.toArray()));
    }

    @Test
    @DisplayName("Hash Test")
    public void hashTest() throws Exception {
        String key = "myKey";
        final HashOperations<String, Object, Object> stringObjectObjectHashOperations = redisTemplate.opsForHash();
        stringObjectObjectHashOperations.put(key, "hello", "hihihi");
        stringObjectObjectHashOperations.put(key, "hello2", "hihihi2");
        stringObjectObjectHashOperations.put(key, "hello3", "hihihi3");

        final Object hello = stringObjectObjectHashOperations.get(key, "hello");
        System.out.println("hello = " + hello);

        final Map<Object, Object> entries = stringObjectObjectHashOperations.entries(key);
        System.out.println("entries = " + entries.get("hello3"));

        final Long size = stringObjectObjectHashOperations.size(key);
        System.out.println("size = " + size);
    }
}
