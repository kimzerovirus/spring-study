package me.kzv.jpabasic.controller;

import lombok.RequiredArgsConstructor;
import me.kzv.jpabasic.controller.dto.MemberCreateReqDto;
import me.kzv.jpabasic.persistence.member.Member;
import me.kzv.jpabasic.persistence.member.MemberReadOnly;
import me.kzv.jpabasic.persistence.member.MemberReadOnlyRepository;
import me.kzv.jpabasic.persistence.member.MemberRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/members")
public class TestController {
    private final MemberReadOnlyRepository memberReadOnlyRepository;
    private final MemberRepository memberRepository;


    @GetMapping
    public ResponseEntity<List<MemberReadOnly>> create(){
        List<MemberReadOnly> memberList = memberReadOnlyRepository.findAll();
        return ResponseEntity.ok().body(memberList);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody MemberCreateReqDto createReqDto){
        Member member = Member.builder()
                .memberName(createReqDto.getMemberName())
                .build();
        memberRepository.save(member);
        return ResponseEntity.ok().build();
    }
}
