//package com.yang.util;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Component;
//
//import java.util.concurrent.TimeUnit;
//
///**
// * redis缓存类
// */
//@Component
//public class RedisUtil {
//
//    @Autowired
//    private RedisTemplate<String, Object> redisTemplate;
//
//    // 添加操作
//    public void set(String key, Object value, Long time){
//        // 保存值
//        redisTemplate.opsForValue().set(key, value);
//        // 如果有设定过期时间，就讲时间设置好
//        if(time != null && time > 0){
//            redisTemplate.expire(key, time, TimeUnit.SECONDS);
//        }
//    }
//
//    // 根据key删除指定的redis中的对象
//    public void deleteByKey(String key){
//        if( key != null){
//            redisTemplate.delete(key);
//        }
//    }
//
//    // 获取存储对象
//    public <T> T get(String key, Class<T> clazz){
//        return (T) redisTemplate.boundValueOps(key).get();
//    }
//
//}
