package com.jf.moonson.starter.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * redis helper
 */
@Slf4j
public class RedisHelper {
    private final RedisTemplate<String, Object> redisTemplate;

    public RedisHelper(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * keys命令替代方案
     */
    public Set<String> keys(String key) {
        // return redisTemplate.execute((RedisCallback<Set<String>>)connection -> {
        //     Set<String> keys = new HashSet<>();
        //     Cursor<byte[]> cursor = connection
        //             .scan(new ScanOptions.ScanOptionsBuilder().match(key).count(1000).build());
        //     while (cursor.hasNext()) {
        //         keys.add(new String(cursor.next()));
        //     }
        //     try {
        //         cursor.close();
        //     } catch (IOException e) {
        //         e.printStackTrace();
        //         log.error("redis cursor close exception, cause:{}, message:{}", e.getCause(), e.getMessage());
        //     }
        //     return keys;
        // });
        return new HashSet<>();
    }
}
