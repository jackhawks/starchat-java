package com.starchat.service.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    private final ObjectMapper objectMapper;

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisUtil(ObjectMapper objectMapper, RedisTemplate<String, Object> redisTemplate) {
        this.objectMapper = objectMapper;
        this.redisTemplate = redisTemplate;
    }

    // String operations

    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void set(String key, Object value, long timeout) {
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    public void set(String key, Object value, long timeout, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public <T> T get(String key, Class<T> clazz) {
        Object value = redisTemplate.opsForValue().get(key);
        return objectMapper.convertValue(value, clazz);
    }

    public boolean setIfAbsent(String key, Object value) {
        return Boolean.TRUE.equals(redisTemplate.opsForValue().setIfAbsent(key, value));
    }

    public boolean setIfAbsent(String key, Object value, long timeout, TimeUnit timeUnit) {
        return Boolean.TRUE.equals(redisTemplate.opsForValue().setIfAbsent(key, value, timeout, timeUnit));
    }

    public Long increment(String key, long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }

    public Long decrement(String key, long delta) {
        return redisTemplate.opsForValue().decrement(key, delta);
    }

    // Hash operations

    public void hSet(String key, String field, Object value) {
        redisTemplate.opsForHash().put(key, field, value);
    }

    public Object hGet(String key, String field) {
        return redisTemplate.opsForHash().get(key, field);
    }

    public <T> T hGet(String key, String field, Class<T> clazz) {
        Object value = redisTemplate.opsForHash().get(key, field);
        return objectMapper.convertValue(value, clazz);
    }

    public Map<Object, Object> hGetAll(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    public <T> Map<String, T> hGetAll(String key, Class<T> clazz) {
        Map<Object, Object> entries = redisTemplate.opsForHash().entries(key);
        return objectMapper.convertValue(entries, objectMapper.getTypeFactory().constructMapType(Map.class, String.class, clazz));
    }

    public void hMSet(String key, Map<String, Object> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    public List<Object> hMGet(String key, List<Object> fields) {
        return redisTemplate.opsForHash().multiGet(key, fields);
    }

    public <T> List<T> hMGet(String key, List<Object> fields, Class<T> clazz) {
        List<Object> values = redisTemplate.opsForHash().multiGet(key, fields);
        return objectMapper.convertValue(values, objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
    }

    public void hDelete(String key, Object... fields) {
        redisTemplate.opsForHash().delete(key, fields);
    }

    public boolean hHasKey(String key, String field) {
        return Boolean.TRUE.equals(redisTemplate.opsForHash().hasKey(key, field));
    }

    public Long hIncrement(String key, String field, long delta) {
        return redisTemplate.opsForHash().increment(key, field, delta);
    }

    // List operations

    public Long lPush(String key, Object value) {
        return redisTemplate.opsForList().leftPush(key, value);
    }

    public Long rPush(String key, Object value) {
        return redisTemplate.opsForList().rightPush(key, value);
    }

    public Object lPop(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    public <T> T lPop(String key, Class<T> clazz) {
        Object value = redisTemplate.opsForList().leftPop(key);
        return objectMapper.convertValue(value, clazz);
    }

    public Object rPop(String key) {
        return redisTemplate.opsForList().rightPop(key);
    }

    public <T> T rPop(String key, Class<T> clazz) {
        Object value = redisTemplate.opsForList().rightPop(key);
        return objectMapper.convertValue(value, clazz);
    }

    public List<Object> lRange(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    public <T> List<T> lRange(String key, long start, long end, Class<T> clazz) {
        List<Object> values = redisTemplate.opsForList().range(key, start, end);
        return objectMapper.convertValue(values, objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
    }

    // Set operations

    public Long sAdd(String key, Object... values) {
        return redisTemplate.opsForSet().add(key, values);
    }

    public Long sRemove(String key, Object... values) {
        return redisTemplate.opsForSet().remove(key, values);
    }

    public Set<Object> sMembers(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    public <T> Set<T> sMembers(String key, Class<T> clazz) {
        Set<Object> values = redisTemplate.opsForSet().members(key);
        return objectMapper.convertValue(values, objectMapper.getTypeFactory().constructCollectionType(Set.class, clazz));
    }

    public boolean sIsMember(String key, Object value) {
        return Boolean.TRUE.equals(redisTemplate.opsForSet().isMember(key, value));
    }

    // Sorted Set operations

    public Boolean zAdd(String key, Object value, double score) {
        return redisTemplate.opsForZSet().add(key, value, score);
    }

    public Long zRemove(String key, Object... values) {
        return redisTemplate.opsForZSet().remove(key, values);
    }

    public Set<Object> zRange(String key, long start, long end) {
        return redisTemplate.opsForZSet().range(key, start, end);
    }

    public <T> Set<T> zRange(String key, long start, long end, Class<T> clazz) {
        Set<Object> values = redisTemplate.opsForZSet().range(key, start, end);
        return objectMapper.convertValue(values, objectMapper.getTypeFactory().constructCollectionType(Set.class, clazz));
    }

    public Long zRank(String key, Object value) {
        return redisTemplate.opsForZSet().rank(key, value);
    }

    // Common operations

    public boolean hasKey(String key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }
}
