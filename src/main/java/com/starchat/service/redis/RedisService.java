package com.starchat.service.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    private final StringRedisTemplate stringRedisTemplate;
    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public RedisService(StringRedisTemplate stringRedisTemplate, RedisTemplate<String, Object> redisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.redisTemplate = redisTemplate;
    }

    // String 操作
    /**
     * 设置字符串键值对
     */
    public void set(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    /**
     * 获取字符串值
     */
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 设置带有过期时间的键值对
     */
    public void setWithExpire(String key, String value, long timeout, TimeUnit unit) {
        stringRedisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    /**
     * 设置带有默认过期时间的键值对
     */
    public void setWithExpire(String key, String value, long timeout) {
        setWithExpire(key, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * 删除键
     */
    public void delete(String key) {
        stringRedisTemplate.delete(key);
    }

    /**
     * 检查键是否存在
     */
    public boolean hasKey(String key) {
        return Boolean.TRUE.equals(stringRedisTemplate.hasKey(key));
    }

    // Object 操作
    /**
     * 设置对象
     */
    public <T> void setObject(String key, T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 获取对象
     */
    public <T> T getObject(String key, Class<T> clazz) {
        Object value = redisTemplate.opsForValue().get(key);
        if (clazz.isInstance(value)) {
            return clazz.cast(value);
        } else {
            throw new IllegalArgumentException("Object is not of type " + clazz.getName());
        }
    }

    // Hash 操作
    /**
     * 设置 Hash 值
     */
    public void hSet(String key, String field, String value) {
        stringRedisTemplate.opsForHash().put(key, field, value);
    }

    /**
     * 获取 Hash 值
     */
    public String hGet(String key, String field) {
        return (String) stringRedisTemplate.opsForHash().get(key, field);
    }

    /**
     * 获取所有 Hash 值
     */
    public Map<Object, Object> hGetAll(String key) {
        return stringRedisTemplate.opsForHash().entries(key);
    }

    // List 操作
    /**
     * 设置 List 值
     */
    public void lPush(String key, String value) {
        stringRedisTemplate.opsForList().leftPush(key, value);
    }

    /**
     * 获取 List 值
     */
    public String lPop(String key) {
        return stringRedisTemplate.opsForList().leftPop(key);
    }
}
