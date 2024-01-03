package com.express.freight.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.stereotype.Component;
import redis.embedded.RedisServer;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.util.Objects;

@Configuration
@EnableRedisRepositories
@Component
public class RedisConfig {

    private String host;
    private int port;
    private RedisServer redisServer;

    public RedisConfig(@Value("${spring.redis.host}") String host,@Value("${spring.redis.port}") int port) {
        this.host = host;
        this.port = port;
    }

    @PostConstruct
    public void startRedis() {

        try {
            if (redisServer == null || !redisServer.isActive()) {
                redisServer = new RedisServer(Objects.requireNonNull(new ClassPathResource("/redis-server-mac-arm64").getFile()), port);
                redisServer.start();
            }
        } catch ( Exception e) {
            e.printStackTrace();
        }

    }

    @PreDestroy
    public void stopRedis() {
        if (redisServer != null) {
            redisServer.stop();
        }
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration redisConfiguration = new RedisStandaloneConfiguration();
        redisConfiguration.setHostName(this.host);
        redisConfiguration.setPort(this.port);
        redisConfiguration.setDatabase(0);
        return new LettuceConnectionFactory(redisConfiguration);
    }

    @Bean(name = "redisTemplate")
    public RedisTemplate<?, ?> redisTemplate() {
        RedisTemplate<?, ?> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        return redisTemplate;
    }

}
