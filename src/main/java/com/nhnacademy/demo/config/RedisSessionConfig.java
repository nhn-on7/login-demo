package com.nhnacademy.demo.config;

import com.nhnacademy.demo.session.RedisSession;
import com.nhnacademy.demo.session.Session;
import com.nhnacademy.demo.session.SessionMember;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

// TODO 9: 프로파일에 따른 Redis 세션 설정
@Profile("redis")
@Configuration
public class RedisSessionConfig {

    @Value("${spring.redis.port}")
    Integer port;

    @Value("${spring.redis.host}")
    String host;

    @Value("${spring.redis.password}")
    String password;

    @Value("${spring.redis.database}")
    Integer database;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {

        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(host, port);

        configuration.setPassword(password);
        configuration.setDatabase(database);

        return new LettuceConnectionFactory(configuration);
    }

    // TODO 10: Redis Template 설정
    @Bean
    public RedisTemplate<String, SessionMember> redisTemplate(RedisConnectionFactory redisConnectionFactory) {

        RedisTemplate<String, SessionMember> redisTemplate = new RedisTemplate<>();

        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(SessionMember.class));

        return redisTemplate;
    }

    // TODO 12: Redis Session Spring Bean 등록
    @Bean
    public Session session(RedisTemplate<String, SessionMember> redisTemplate) {
        return new RedisSession(redisTemplate);
    }

}
