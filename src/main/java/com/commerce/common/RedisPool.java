package com.commerce.common;

import com.commerce.util.PropertiesUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisPool {

    // jedis连接池
    private static JedisPool pool;

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

    // redis server host
    private static String ip = PropertiesUtil.getProperty("redis.ip");

    // redis server port
    private static Integer port = Integer.parseInt(PropertiesUtil.getProperty("redis.port", "6379"));


    private static void initPool() {

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMinIdle(minIdle);
        jedisPoolConfig.setTestOnBorrow(testOnBorrow);
        jedisPoolConfig.setTestOnReturn(testOnReturn);

        // 连接耗尽是否阻塞等待
        jedisPoolConfig.setBlockWhenExhausted(true);

        pool = new JedisPool(jedisPoolConfig, ip, port, 2_000);

    }

    static {
        initPool();
    }

    public static Jedis getJedis() {
        return pool.getResource();
    }

    public static void returnResource(Jedis jedis) {
        pool.returnResource(jedis);
    }

    public static void returnBrokenResource(Jedis jedis) {
        pool.returnBrokenResource(jedis);
    }

    // @Test
    public static void main(String[] args) {
        Jedis jedis = pool.getResource();

        jedis.set("jedisKey", "jedisValue");

        System.out.println(jedis.get("jedisKey"));

        returnResource(jedis);

        pool.destroy();

        System.out.println("over");
    }

}
