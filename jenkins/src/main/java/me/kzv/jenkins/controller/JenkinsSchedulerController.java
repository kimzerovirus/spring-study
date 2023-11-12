package me.kzv.jenkins.controller;

import lombok.RequiredArgsConstructor;
import me.kzv.jenkins.persistence.TestId;
import me.kzv.jenkins.persistence.TestIdRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@RequiredArgsConstructor
@RestController
public class JenkinsSchedulerController {
    private final TestIdRepository testIdRepository;

    @GetMapping("test")
    public String test(){
        return "test";
    }

    @PostMapping("/schedule")
    public ResponseEntity<String> registerEvery1Min(){
        testIdRepository.save(new TestId());
        var now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return ResponseEntity.ok().body("test is success!! ... " + now);
    }
}
