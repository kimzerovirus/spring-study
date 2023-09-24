package com.example.springmysql.web.domain.member;

import com.example.springmysql.web.domain.member.dto.MemberDto;
import com.example.springmysql.web.domain.member.dto.MemberNicknameHistoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MemberReadService {
    private final MemberRepository memberRepository;

    private final MemberNicknameHistoryRepository memberNicknameHistoryRepository;

    public MemberDto getMember(Long memberId) {
        var member = memberRepository.findById(memberId).orElseThrow();
        return toDto(member);
    }

    public List<MemberDto> getMembers(List<Long> memberIds) {
        var members = memberRepository.findAllByIdIn(memberIds);
        return members.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<MemberNicknameHistoryDto> getNicknameHistories(Long memberId) {
        var histories = memberNicknameHistoryRepository.findAllByMemberId(memberId);
        return histories.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public MemberDto toDto(Member member) {
        return new MemberDto(member.getId(), member.getNickname(), member.getEmail(), member.getBirthday());
    }

    public MemberNicknameHistoryDto toDto(MemberNicknameHistory history) {
        return new MemberNicknameHistoryDto(
                history.getId(),
                history.getMemberId(),
                history.getNickname(),
                history.getCreatedAt()
        );

    }
}
