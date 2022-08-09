package com.nhnacademy.demo.session;

import com.nhnacademy.demo.entity.Member;
import org.springframework.stereotype.Service;

// TODO 4: 세션을 다루기 위한 인터페이스
@Service
public interface Session {

    void login(Member member, String sessionId);

    SessionMember findMember(String sessionId);

    void logout(String sessionId);

}
