package com.nhnacademy.demo.config;

import com.nhnacademy.demo.session.RdbSessionRepository;
import com.nhnacademy.demo.session.RdbSession;
import com.nhnacademy.demo.session.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

// TODO 13: 프로파일에 따른 RDB 세션 설정
@Profile("rdb")
@Configuration
@RequiredArgsConstructor
public class RdbSessionConfig {

    private final RdbSessionRepository rdbSessionRepository;

    // TODO 15: RBD 세션 Spring Bean 등록
    @Bean
    public Session session() {
        return new RdbSession(rdbSessionRepository);
    }

}
