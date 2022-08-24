package me.kzv.core1.old;

import me.kzv.core1.old.member.Grade;
import me.kzv.core1.old.member.Member;
import me.kzv.core1.old.member.MemberService;

public class MemberApp {
    public static void main(String[] args) {
        MemberService memberService = new AppConfig().memberService();
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("findMember = " + findMember);
        System.out.println("member = " + member);
    }
}
