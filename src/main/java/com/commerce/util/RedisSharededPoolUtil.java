package com.commerce.util;

import com.commerce.common.RedisSharededPool;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.ShardedJedis;

@Slf4j
public class RedisSharededPoolUtil {

    public static Long expire(String key, int exTimeSeconds) {
        ShardedJedis jedis = null;
        Long result = null;
        try {
            jedis = RedisSharededPool.getJedis();
            result = jedis.expire(key, exTimeSeconds);
        } catch (Exception e) {
            log.error("expire key:{} error", key, e);
            RedisSharededPool.returnBrokenResource(jedis);
            return result;
        }
        RedisSharededPool.returnResource(jedis);
        return result;
    }

    public static String setEx(String key, String value, int exTimeSeconds) {
        ShardedJedis jedis = null;
        String result = null;
        try {
            jedis = RedisSharededPool.getJedis();
            result = jedis.setex(key, exTimeSeconds, value);
        } catch (Exception e) {
            log.error("setex key:{} value:{} error", key, value, e);
            RedisSharededPool.returnBrokenResource(jedis);
            return result;
        }
        RedisSharededPool.returnResource(jedis);
        return result;
    }

    public static String set(String key, String value) {
        ShardedJedis jedis = null;
        String result = null;

        try {
            jedis = RedisSharededPool.getJedis();
            result = jedis.set(key, value);
        } catch (Exception e) {
            log.error("set key:{} value:{} error", key, value, e);
            RedisSharededPool.returnBrokenResource(jedis);
            return result;
        }
        RedisSharededPool.returnResource(jedis);
        return result;
    }

    public static String get(String key) {
        ShardedJedis jedis = null;
        String result = null;
        try {
            jedis = RedisSharededPool.getJedis();
            result = jedis.get(key);
        } catch (Exception e) {
            log.error("get key:{} error", key, e);
            RedisSharededPool.returnBrokenResource(jedis);
            return result;
        }
        RedisSharededPool.returnResource(jedis);
        return result;
    }

    public static Long del(String key) {
        ShardedJedis jedis = null;
        Long result = null;
        try {
            jedis = RedisSharededPool.getJedis();
            result = jedis.del(key);
        } catch (Exception e) {
            log.error("del key:{} error", key, e);
            RedisSharededPool.returnBrokenResource(jedis);
            return result;
        }
        RedisSharededPool.returnResource(jedis);
        return result;
    }


    public static String getSet(String key, String value) {
        ShardedJedis jedis = null;
        String result = null;
        try {
            jedis = RedisSharededPool.getJedis();
            result = jedis.getSet(key, value);
        } catch (Exception e) {
            log.error("getset key:{} error", key, e);
            RedisSharededPool.returnBrokenResource(jedis);
            return result;
        }
        RedisSharededPool.returnResource(jedis);
        return result;
    }

    public static Long setnx(String key, String value) {
        ShardedJedis jedis = null;
        Long result = null;
        try {
            jedis = RedisSharededPool.getJedis();
            result = jedis.setnx(key, value);
        } catch (Exception e) {
            log.error("setnx key:{} value:{} error", key, value, e);
            RedisSharededPool.returnBrokenResource(jedis);
            return result;
        }
        RedisSharededPool.returnResource(jedis);
        return result;
    }


    public static void main(String[] args) {
        ShardedJedis jedis = RedisSharededPool.getJedis();

        RedisSharededPoolUtil.set("keyTest", "value");

        String value = RedisSharededPoolUtil.get("keyTest");

        RedisSharededPoolUtil.setEx("keyex", "valueex", 60 * 10);

        RedisSharededPoolUtil.expire("keyTest", 60 * 20);

        RedisSharededPoolUtil.del("keyTest");


        String s = RedisSharededPoolUtil.get("keyex");
        System.out.println(s);

        System.out.println("end");

    }


}