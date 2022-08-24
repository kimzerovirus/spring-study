package me.kzv.core1.old.member;

public interface MemberService {

    void join(Member member);

    Member findMember(Long memberId);
}
