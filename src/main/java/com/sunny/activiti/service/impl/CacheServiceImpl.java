package com.sunny.activiti.service.impl;

import com.sunny.activiti.service.ICacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class CacheServiceImpl implements ICacheService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void cacheStringData(String cacheKey, String cacheValue, long timeout) {
        stringRedisTemplate.opsForValue().set(cacheKey, cacheValue,timeout, TimeUnit.MINUTES);//分钟过期
    }

    @Override
    public String getCacheInfoByCode(String cacheKey) {
        return stringRedisTemplate.opsForValue().get(cacheKey);
    }

    @Override
    public void cacheObjData(String cacheKey, Object cacheValue, long timeout) {
        redisTemplate.opsForValue().set(cacheKey, cacheValue, timeout, TimeUnit.MINUTES);
    }

    @Override
    public Object getObjCacheByCode(String cacheKey) {
        return redisTemplate.opsForValue().get(cacheKey);
    }

    @Override
    public boolean delCacheByCode(String cacheKey) {
        return redisTemplate.delete(cacheKey);
    }
}
