package com.nhnacademy.demo.controller;

import com.nhnacademy.demo.entity.Member;
import com.nhnacademy.demo.repository.MemberRepository;
import com.nhnacademy.demo.session.Session;
import com.nhnacademy.demo.session.SessionMember;
import java.util.Objects;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

// TODO 5: 컨트롤러 설정 login, logout
@Controller
public class IndexController {

    private static final String SESSION_ID = "session-id";

    private final Session session;
    private final MemberRepository repository;

    public IndexController(Session session, MemberRepository repository) {
        this.session = session;
        this.repository = repository;
    }

    @GetMapping("/")
    public String index(@CookieValue(required = false, name = SESSION_ID) Cookie cookie, Model model) {
        if (Objects.nonNull(cookie)) {
            SessionMember member = session.findMember(cookie.getValue());
            model.addAttribute("member", member);
        }

        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login-form";
    }

    @PostMapping("/login")
    public String doLogin(@ModelAttribute Member member, HttpSession httpSession, HttpServletResponse response) {
        String sessionId = httpSession.getId();
        Member member1 = repository.login(member.getUsername(), member.getPassword())
                                   .orElseThrow(() -> new IllegalArgumentException("로그인 실패!"));

        session.login(member1, sessionId);

        Cookie cookie = new Cookie(SESSION_ID, sessionId);
        cookie.setHttpOnly(true);

        response.addCookie(cookie);

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(@CookieValue(required = false, name = SESSION_ID) Cookie cookie) {
        if (Objects.nonNull(cookie)) {
            session.logout(cookie.getValue());
        }
        return "redirect:/";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String error(IllegalArgumentException e, Model model) {
        model.addAttribute("error", e.getMessage());
        return "error";
    }

}
