package com.commerce.common;

import com.commerce.util.PropertiesUtil;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class RedissonManager {

    //在redis环境没有搭建起来之前，这里先注释上，否则项目启动不起来。
    private Config config = new Config();

    private Redisson redisson = null;

    private final static String redisClusterAddresses = PropertiesUtil.getProperty("redis.cluster.hosts");

    // 注入到Spring容器的话，使用@PostConstruct或者静态块初始化，效果是一样的
    @PostConstruct
    private void init() {
        try {
            // 分布式redis
            config.useSingleServer().setAddress(redisClusterAddresses.split(",")[0]);
            // 分布式redis
//            config.useClusterServers().addNodeAddress(redisClusterAddresses.split(","));

            redisson = (Redisson) Redisson.create(config);
            log.info("初始化Redisson结束");
        } catch (Exception e) {
            log.error("redisson init error", e);
        }
    }

    public Redisson getRedisson() {
        return redisson;
    }

}