package me.kzv.jdbcjpas3.test;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TestApiController {
    private final TestService testService;

    @GetMapping("/test")
    public List<TestMember> getAllMembers(){
        return testService.getAllTestMembers();
    }
}
