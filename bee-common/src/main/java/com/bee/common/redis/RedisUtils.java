package com.bee.common.redis;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.print.attribute.standard.Finishings;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Bruce
 * @create 2023/12/06
 * @description Redis工具类
 */
@Component
public class RedisUtils {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 默认过期时间设置为24小时，单位：s
     */
    private final static long DEFAULT_EXPIRE = 60 * 60 * 24L;

    /**
     * 过期时间设置为1小时，单位：s
     */
    private final static long HOUR_ONE_EXPIRE = 60 * 60 * 1L;
    /**
     * 过期时间设置为6小时，单位：s
     */
    private final static long HOUR_SIX_ONE_EXPIRE = 60 * 60 * 6L;
    /**
     * 不设置过期时间
     */
    private final static long NOT_EXPIRE = -1L;

    public void set(String key, Object value) {
        set(key, value, DEFAULT_EXPIRE);
    }

    public void set(String key, Object value, long expire) {
        redisTemplate.opsForValue().set(key, value);
        setExpire(key, expire);
    }

    public Object get(String key) {
        return get(key, NOT_EXPIRE);
    }

    public Object get(String key, long expire) {
        Object value = redisTemplate.opsForValue().get(key);
        setExpire(key, expire);
        return value;
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }

    public void delete(Collection<String> keys) {
        redisTemplate.delete(keys);
    }

    public Object hGet(String key, String field) {
        return redisTemplate.opsForHash().get(key, field);
    }

    public Map<String, Object> hGetAll(String key) {
        HashOperations<String, String, Object> hashOperations = redisTemplate.opsForHash();
        return hashOperations.entries(key);
    }

    public void hMSet(String key, Map<String, Object> map) {
        hMSet(key, map, DEFAULT_EXPIRE);
    }

    public void hMSet(String key, Map<String, Object> map, long expire) {
        redisTemplate.opsForHash().putAll(key, map);
        setExpire(key, expire);
    }

    public void hSet(String key, String field, Object value) {
        hSet(key, field, value, DEFAULT_EXPIRE);
    }

    public void hSet(String key, String field, Object value, long expire) {
        redisTemplate.opsForHash().put(key, field, value);
        setExpire(key, expire);
    }

    public void hDel(String key, Object... fields) {
        redisTemplate.opsForHash().delete(key, fields);
    }

    public void leftPush(String key, Object value) {
        leftPush(key, value, DEFAULT_EXPIRE);
    }

    public void leftPush(String key, Object value, long expire) {
        redisTemplate.opsForList().leftPush(key, value);
        setExpire(key, expire);
    }

    public Object rightPop(String key) {
        return redisTemplate.opsForList().rightPop(key);
    }

    public void expire(String key, long expire) {
        redisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }

    public void setExpire(String key, long expire) {
        if (NOT_EXPIRE != expire) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
    }

}
