package com.gxb.modules.cache.service;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheElement;
import org.springframework.data.redis.cache.RedisCacheKey;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashSet;
import java.util.Set;

/**
 * time : 15/11/11.
 * auth : jqwang
 * desc :
 * tips :
 * 1.
 */
public class RedisTestCacheService1 {


    @Autowired
    private RedisCache redisCache1;

    @Autowired
    private RedisCache redisCache2;

    int redisTemplateCount = 2;

    private String[] defaultKeys = {"1","2"};

    private RedisCache getRedisCache(String key){
        RedisCache redisCache = null;
        int code = key.hashCode();
        if(code%redisTemplateCount==0){
            redisCache = redisCache1;
        }else {
            redisCache = redisCache2;
        }
        return redisCache;
    }

    public <T> T get(String key, Class<T> clazz) {
        return getRedisCache(key).get(key, clazz);
    }

    public void put(String key, Object value){
        getRedisCache(key).put(key, value);
    }

    public void putIfAbsent(String key, Object value){
        getRedisCache(key).putIfAbsent(key, value);
    }

    public void put(String key, Object value,long expireTime) {

        RedisCacheKey redisCacheKey = new RedisCacheKey(key);
        RedisCacheElement element = new RedisCacheElement(redisCacheKey,value);
        element.expireAfter(expireTime);
        getRedisCache(key).put(element);
    }

    public void putIfAbsent(String key, Object value,long expireTime){
        RedisCacheKey redisCacheKey = new RedisCacheKey(key);
        RedisCacheElement element = new RedisCacheElement(redisCacheKey,value);
        element.expireAfter(expireTime);
        getRedisCache(key).putIfAbsent(element);
    }

    public void remove (String key){
        getRedisCache(key).evict(key);
    }

    public Set<String> getKeys(String pattern){
        Set<String> set = new HashSet<>();
        for (int i=0;i<defaultKeys.length;i++){
            RedisTemplate<String, Object> redisTemplate = (RedisTemplate<String, Object>)getRedisCache(defaultKeys[i]).getNativeCache();
            set.addAll(redisTemplate.keys(pattern));
        }
        return set;
    }

    public void removeAll(@NotBlank String pattern){
        Set<String> keySet = getKeys(pattern);
        for(String key :keySet){
            remove(key);
        }
    }

    public Long getExpire(@NotBlank String key){
        RedisCacheKey redisCacheKey = new RedisCacheKey(key);
        RedisCacheElement element = getRedisCache(key).get(redisCacheKey);
        long time =  element.getTimeToLive();

        RedisTemplate<String, Object> redisTemplate = (RedisTemplate<String, Object>)getRedisCache(key).getNativeCache();
        return redisTemplate.getExpire(key);
    }

    public Boolean existsKey(@NotBlank String key){
        boolean hasKey = false;
        for (int i=0;i<defaultKeys.length;i++){
            RedisTemplate<String, Object> redisTemplate = (RedisTemplate<String, Object>)getRedisCache(defaultKeys[i]).getNativeCache();
            hasKey = redisTemplate.hasKey(key);
            if(hasKey){
                break;
            }
        }
        return hasKey ;
    }
}
