package com.example.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
public class HelloController {

    private final RedisTemplate redisTemplate;

    @GetMapping("/hello")
    public String hello(HttpSession session) {
        session.setAttribute("hello", "hello"); // 세션 생성
        session.getAttribute("hello"); // 세션 정보 가져오기
        session.removeAttribute("hello"); // 세션 삭제

        return "hello";
    }

    @GetMapping("/set-fruit")
    public ResponseEntity<String> setFruit(@RequestParam String fruit) {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.set("fruit", fruit);

        return ResponseEntity.ok("saved");
    }

    @GetMapping("/get-fruit")
    public ResponseEntity<String> getFruit() {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String fruit = ops.get("fruit");

        return ResponseEntity.ok(fruit);
    }
}
