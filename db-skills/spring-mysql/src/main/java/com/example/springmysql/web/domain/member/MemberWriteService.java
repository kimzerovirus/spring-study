package com.example.springmysql.web.domain.member;

import com.example.springmysql.web.domain.member.command.RegisterMemberCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class MemberWriteService {
    private final MemberRepository memberRepository;
    private final MemberNicknameHistoryRepository memberNicknameHistoryRepository;

    /**
     * 목표
     * - 회원 정보(이메일, 닉네임, 생년월일)을 등록한다.
     * - 닉네임은 10자를 넘길수 없다.
     * param - memberRegisterCommand
     * */
    @Transactional
    public Member create(RegisterMemberCommand command) {
        Member member = Member.builder()
                .nickname(command.getNickname())
                .email(command.getEmail())
                .birthday(command.getBirthday())
                .build();

        Member savedMember = memberRepository.save(member);
        saveMemberNicknameHistory(savedMember);

        return savedMember;
    }

    @Transactional
    public void changeNickname(Long memberId, String nickname) {
        var member = memberRepository.findById(memberId).orElseThrow();
        member.changeNickname(nickname);
        var savedMember = memberRepository.save(member);

        saveMemberNicknameHistory(savedMember);
    }

    private void saveMemberNicknameHistory(Member member) {
        var history = MemberNicknameHistory
                .builder()
                .memberId(member.getId())
                .nickname(member.getNickname())
                .build();

        memberNicknameHistoryRepository.save(history);
    }
}
