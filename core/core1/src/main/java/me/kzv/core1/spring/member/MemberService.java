package me.kzv.core1.spring.member;

public interface MemberService {

    void join(Member member);

    Member findMember(Long memberId);
}
