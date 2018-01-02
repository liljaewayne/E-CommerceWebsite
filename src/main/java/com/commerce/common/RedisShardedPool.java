package com.commerce.common;

import com.commerce.util.PropertiesUtil;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.util.Hashing;
import redis.clients.util.Sharded;

import java.util.ArrayList;
import java.util.List;

public class RedisShardedPool {
    // sharded jedis连接池
    private static ShardedJedisPool pool;

    // 最大连接数
    private static Integer maxTotal = Integer.parseInt(PropertiesUtil.getProperty("redis.max.total", "20"));

    // 最大空闲实例数
    private static Integer maxIdle = Integer.parseInt(PropertiesUtil.getProperty("redis.max.idle", "10"));

    // 最小空闲实例数
    private static Integer minIdle = Integer.parseInt(PropertiesUtil.getProperty("redis.min.idle", "2"));

    // 在borrow一个jedis实例的时候, 是否要进行验证操作
    // 如果赋值为true, 则得到的jedis实例肯定是可以用的
    private static Boolean testOnBorrow = Boolean.parseBoolean(PropertiesUtil.getProperty("redis.test.borrow", "true"));

    // 在return一个jedis实例的时候, 是否要进行验证操作
    // 如果赋值为true, 则放回JedisPool的jedis实例肯定是可以用的
    private static Boolean testOnReturn = Boolean.parseBoolean(PropertiesUtil.getProperty("redis.test.return", "false"));

    private static void initPool() {
        JedisPoolConfig config = new JedisPoolConfig();

        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMinIdle(minIdle);

        config.setTestOnBorrow(testOnBorrow);
        config.setTestOnReturn(testOnReturn);

        // 连接耗尽的时候，是否阻塞，false会抛出异常，true阻塞直到超时。默认为true。
        config.setBlockWhenExhausted(true);


        String redisClusterHosts = PropertiesUtil.getProperty("redis.cluster.hosts");

        String[] split = redisClusterHosts.split(",");

        List<JedisShardInfo> jedisShardInfoList = new ArrayList<JedisShardInfo>(split.length);
        for (String host : split) {
            JedisShardInfo jedisShardInfo = new JedisShardInfo(host.split(":")[0], Integer.parseInt(host.split(":")[1]), 2_000);
            jedisShardInfoList.add(jedisShardInfo);
        }

        pool = new ShardedJedisPool(config, jedisShardInfoList, Hashing.MURMUR_HASH, Sharded.DEFAULT_KEY_TAG_PATTERN);
    }

    static {
        initPool();
    }

    public static ShardedJedis getJedis() {
        return pool.getResource();
    }


    public static void returnBrokenResource(ShardedJedis jedis) {
        pool.returnBrokenResource(jedis);
    }


    public static void returnResource(ShardedJedis jedis) {
        pool.returnResource(jedis);
    }


    public static void main(String[] args) {
        ShardedJedis jedis = pool.getResource();

        for (int i = 0; i < 10; i++) {
            jedis.set("key" + i, "value" + i);
        }
        returnResource(jedis);

//        pool.destroy();// 临时调用，销毁连接池中的所有连接
        System.out.println("program is end");


    }
}