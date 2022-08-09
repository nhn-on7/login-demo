package com.nhnacademy.demo.session;

import com.nhnacademy.demo.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;


// TODO 11: 세션을 Redis 로 사용 할 때 Spring Bean 에 등록된다.
@RequiredArgsConstructor
public class RedisSession implements Session {

    private static final String HASH_KEY = "member";

    private final RedisTemplate<String, SessionMember> redisTemplate;

    @Override
    public void login(Member member, String sessionId) {
        SessionMember sessionMember = new SessionMember(sessionId, member.getUsername(), member.getPassword());
        redisTemplate.opsForHash().put(sessionId, HASH_KEY, sessionMember);
    }

    @Override
    public SessionMember findMember(String sessionId) {
        return (SessionMember) redisTemplate.opsForHash().get(sessionId, HASH_KEY);
    }

    @Override
    public void logout(String sessionId) {
        redisTemplate.opsForHash().delete(sessionId, HASH_KEY);
    }

}
