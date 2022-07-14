package me.kzv.demo.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import me.kzv.demo.web.domain.ClubMember;
import me.kzv.demo.web.domain.ClubMemberRole;
import me.kzv.demo.web.repository.ClubMemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/sample/test")
public class SampleApiController {

    private final ClubMemberRepository repository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public ClubMember exTest(){
        ClubMember clubMember = ClubMember.builder().email("user@aaa.com")
                .name("사용자").fromSocial(false).password(passwordEncoder.encode("1111")).build();
        clubMember.addMemberRole(ClubMemberRole.USER);
        return repository.save(clubMember);
    }

    @GetMapping("/all")
    public void exAll() {
        log.info("exAll.........");
    }

    @GetMapping("/member")
    public void exMember(){
        log.info("exMember..........");
    }


    @GetMapping("/admin")
    public void exAdmin(){
        log.info("exAdmin............");
    }
}
