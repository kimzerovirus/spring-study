package me.kzv.jenkins.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    @GetMapping("/get-test")
    public ResponseEntity<String> getTest(@RequestParam String test) {
        return ResponseEntity.ok(test);
    }

    @PostMapping("/post-test")
    public ResponseEntity<String> postTest(@RequestBody String test) {
        return ResponseEntity.ok(test);
    }
}
