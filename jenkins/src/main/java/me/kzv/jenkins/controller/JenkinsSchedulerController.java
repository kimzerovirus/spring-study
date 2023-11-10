package me.kzv.jenkins.controller;

import lombok.RequiredArgsConstructor;
import me.kzv.jenkins.persistence.TestId;
import me.kzv.jenkins.persistence.TestIdRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class JenkinsSchedulerController {
    private final TestIdRepository testIdRepository;

    @PostMapping("/schedule")
    public ResponseEntity<Void> registerEvery1Min(){
        testIdRepository.save(new TestId());

        return ResponseEntity.ok().build();
    }
}
