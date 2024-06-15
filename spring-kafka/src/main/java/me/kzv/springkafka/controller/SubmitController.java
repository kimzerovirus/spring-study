package me.kzv.springkafka.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.kzv.springkafka.domain.Member;
import me.kzv.springkafka.dto.JoinMemberDto;
import me.kzv.springkafka.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class SubmitController {
    private final MemberService memberService;

    @PostMapping("/submit/join")
    public String submitJoin(JoinMemberDto dto, Model model) {
        Member member = memberService.join(dto);
        model.addAttribute("memberId", member.getId());
        model.addAttribute("memberName", member.getMemberName());
        model.addAttribute("organizationName", member.getOrganizationName());
        model.addAttribute("memo", member.getMemo());
        model.addAttribute("createdAt", member.getCreatedAt().toLocalDateTime());
        return "joinSuccess";
    }
}
