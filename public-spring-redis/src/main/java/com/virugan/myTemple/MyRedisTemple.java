package com.virugan.myTemple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class MyRedisTemple {
    
    @Autowired
    RedisTemplate redisTemplate;

    public void saveObject(String key,Object object){
        redisTemplate.opsForValue().set(key,object);
    }

    public <T> T findObject(String key,Class<T> t){
        T o = (T)redisTemplate.opsForValue().get(key);
        return o;
    }
}
