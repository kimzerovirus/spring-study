package com.example.redis;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/http-session")
@RestController
public class HttpSessionLoginController {

    private final Map<String, String> sessionMap = new HashMap<>();

    @GetMapping("/login")
    public String login(HttpSession session, @RequestParam String name) {
        sessionMap.put(session.getId(), name);

        return "saved.";
    }

    @GetMapping("/my-name")
    public String myName(HttpSession session) {
        return sessionMap.get(session.getId());
    }
}
