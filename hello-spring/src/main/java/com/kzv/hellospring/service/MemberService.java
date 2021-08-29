package com.kzv.hellospring.service;

import com.kzv.hellospring.domain.Member;
import com.kzv.hellospring.repository.MemberRepository;
import com.kzv.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service
public class MemberService {

    //    private final MemberRepository memberRepository = new MemoryMemberRepository(); //service와 test의 리포지터리가 다른 인스턴스이므로 같게 만들어주자
    private final MemberRepository memberRepository;

//    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /** 회원가입 */
    public Long join(Member member) {
        long start = System.currentTimeMillis();
        try {
            //중복 회원 검증
            validateDepulicateMemeber(member);
            memberRepository.save(member);
            return member.getId();
        }finally{
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join="+timeMs+"ms");
        }
    }

    private void validateDepulicateMemeber(Member member) {
        //같은 이름이 있는 중복 회원x
        Optional<Member> result = memberRepository.findByName(member.getName()); //null체크 optional로 편하게
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });

//        요렇게 쓰면 더 깔끔하다.
//        memberRepository.findByName(member.getName())
//                .ifPresent(m->{
//                    throw new IllegalStateException("이미 존재하는 회원입니다.")
//                });
    }

    /** 전체 회원 조회 */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
