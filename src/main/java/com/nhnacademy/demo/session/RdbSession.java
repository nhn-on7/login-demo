package com.nhnacademy.demo.session;

import com.nhnacademy.demo.entity.Member;
import lombok.RequiredArgsConstructor;

// TODO 14: 세션을 RDB 로 사용할 때 Spring Bean 에 등록된다.
@RequiredArgsConstructor
public class RdbSession implements Session {

    private final RdbSessionRepository rdbSessionRepository;

    @Override
    public void login(Member member, String sessionId) {

        SessionMember sessionMember = new SessionMember(sessionId, member.getUsername(), member.getPassword());
        rdbSessionRepository.save(sessionMember);
    }

    @Override
    public SessionMember findMember(String sessionId) {

        return rdbSessionRepository.findById(sessionId)
                                   .orElse(null);
    }

    @Override
    public void logout(String sessionId) {

        rdbSessionRepository.findById(sessionId)
                            .ifPresent(rdbSessionRepository::delete);
    }

}
