package me.kzv.sampleservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
//@RequestMapping("/sample-service") // gateway 패턴 등록할 때 sample-service 를 제거하도록 변경하였다.
public class TestController {
    private final Environment env;

    @GetMapping("/health-check")
    public ResponseEntity<Map<String, String>> health() {
        var map = new HashMap<String, String>();
        map.put("applicationName", env.getProperty("spring.application.name"));
        map.put("localPort", env.getProperty("local.server.port"));
        map.put("serverPort", env.getProperty("server.port"));

        return ResponseEntity.ok(map);
    }
}
