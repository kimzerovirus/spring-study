package com.example.redis;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 *  implementation 'org.springframework.session:spring-session-data-redis' 의존성 추가 후
 *  spring.session.storage-type=redis 로 설정해주면 HttpSession 을 redis 로 변경해준다.
 */

@RequestMapping("/redis-session")
@RestController
public class RedisSessionLoginController {

    @GetMapping("/login")
    public String login(HttpSession session, @RequestParam String name) {
        session.setAttribute("name", name);

        return "saved.";
    }

    @GetMapping("/my-name")
    public String myName(HttpSession session) {
        return (String) session.getAttribute("name");
    }
}
