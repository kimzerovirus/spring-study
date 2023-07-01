package me.kzv.jdbcjpas3.test;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {
    private final TestMemberRepository testMemberRepository;
    public List<TestMember> getAllTestMembers() {
        return testMemberRepository.findAll();
    }
}
