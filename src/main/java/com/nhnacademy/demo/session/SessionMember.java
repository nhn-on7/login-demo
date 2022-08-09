package com.nhnacademy.demo.session;

import java.io.Serializable;
import javax.persistence.Entity;

import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// TODO 8: 세션에 저장할 클래스
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionMember implements Serializable {

    @Id
    String sessionId;

    String username;
    String password;

}
