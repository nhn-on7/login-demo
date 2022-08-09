package com.nhnacademy.demo.repository;

import com.nhnacademy.demo.entity.Member;
import java.util.Optional;

public interface MemberRepository {

    Optional<Member> login(String username, String password);

}
