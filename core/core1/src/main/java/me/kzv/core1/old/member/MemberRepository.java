package me.kzv.core1.old.member;

public interface MemberRepository {

    void save(Member member);

    Member findById(Long memberId);
}
