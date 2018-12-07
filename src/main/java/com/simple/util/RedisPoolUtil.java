package com.simple.util;

import com.simple.common.RedisPool;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

/**
 * Create by S I M P L E on 2018/03/20 13:36:57
 */
@Slf4j
public class RedisPoolUtil {

    public static String set(String key, String value) {
        Jedis jedis = null;
        String result;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.set(key, value);
        } catch (Exception e) {
            log.error("set key:{},value:{} error", key, value, e);
            RedisPool.returnBlockResource(jedis);
            return null;
        }
        RedisPool.returnResouce(jedis);
        return result;
    }

    public static String get(String key) {
        Jedis jedis = null;
        String result;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.get(key);
        } catch (Exception e) {
            log.error("get key:{} error", key, e);
            RedisPool.returnBlockResource(jedis);
            return null;
        }
        RedisPool.returnResouce(jedis);
        return result;
    }

    public static Long del(String key) {
        Jedis jedis = null;
        Long result;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.del(key);
        } catch (Exception e) {
            log.error("del key:{} error", key, e);
            RedisPool.returnBlockResource(jedis);
            return null;
        }
        RedisPool.returnResouce(jedis);
        return result;
    }

    public static String setEx(String key, String value, int exTime) {
        Jedis jedis = null;
        String result;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.setex(key, exTime, value);
        } catch (Exception e) {
            log.error("setEx key:{} value:{} time:{} error", key, value, exTime, e);
            RedisPool.returnBlockResource(jedis);
            return null;
        }
        RedisPool.returnResouce(jedis);
        return result;
    }

    public static Long expire(String key,int exTime){
        Jedis jedis = null;
        Long result;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.expire(key, exTime);
        } catch (Exception e) {
            log.error("expire key:{} time:{} error", key, exTime);
            RedisPool.returnBlockResource(jedis);
            return null;
        }
        RedisPool.returnResouce(jedis);
        return result;
    }

    public static void main(String[] args) {
        String test = RedisPoolUtil.set("simple","123");
        System.out.println(test);
    }
}
