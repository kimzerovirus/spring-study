package me.kzv.datajpa.controller;

import lombok.RequiredArgsConstructor;
import me.kzv.datajpa.dto.MemberDto;
import me.kzv.datajpa.entity.Member;
import me.kzv.datajpa.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;

    @GetMapping("/api/v1/members/{id}")
    public String findMember(@PathVariable("id") Long id) {
        Member member = memberRepository.findById(id).get();
        return member.getUsername();
    }

    @GetMapping("/api/v2/members/{id}")
    public String findMember2(@PathVariable("id") Member member) { // 도메인 클래스 컨버터
        return member.getUsername();
    }

    @GetMapping("/paging")
    public Page<MemberDto> paging(@PageableDefault(size = 5) Pageable pageable) {
        // pageable 을 파라미터로 받으면 /paging?page=0&sort=id,desc&sort=username,desc 과 같이 요청을 보내면 해당 페이지 리스트를 보내준다. (PageRequest 객체가 알아서 매핑해 주기 때문)
        // cmd opt N 코드 압축하는? 단축키
//        return memberRepository.findAll(pageable)
//                .map(member -> new MemberDto(member.getId(), member.getUsername(), null));
        return memberRepository.findAll(pageable)
                .map(MemberDto::new);
    }

    @PostConstruct
    public void init() {
        memberRepository.save(new Member("userA"));
    }
}
