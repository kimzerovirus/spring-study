package com.example.springmysql.web.application.controller;

import com.example.springmysql.web.domain.member.MemberReadService;
import com.example.springmysql.web.domain.member.MemberWriteService;
import com.example.springmysql.web.domain.member.command.RegisterMemberCommand;
import com.example.springmysql.web.domain.member.dto.MemberDto;
import com.example.springmysql.web.domain.member.dto.MemberNicknameHistoryDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "회원정보")
@RequiredArgsConstructor
@RestController
@RequestMapping("/members")
public class MemberController {
    final private MemberWriteService memberWriteService;
    final private MemberReadService memberReadService;


    @Operation(summary = "회원정보 등록")
    @PostMapping
    public MemberDto register(@RequestBody RegisterMemberCommand command) {
        var member = memberWriteService.create(command);
        return memberReadService.toDto(member);
    }

    @Operation(summary = "회원정보 단건 조회")
    @GetMapping("/{id}")
    public MemberDto getMember(@PathVariable Long id) {
        return memberReadService.getMember(id);
    }

    @Operation(summary = "회원이름 변경")
    @PostMapping("/{id}/name")
    public MemberDto changeNickname(
            @PathVariable Long id,
            @RequestBody String name
    ) {
        memberWriteService.changeNickname(id, name);
        return memberReadService.getMember(id);
    }

    @Operation(summary = "회원이름 변경내역 조회")
    @GetMapping("/{id}/name-histories")
    public List<MemberNicknameHistoryDto> getMemberNameHistories(@PathVariable Long id) {
        return memberReadService.getNicknameHistories(id);
    }
}