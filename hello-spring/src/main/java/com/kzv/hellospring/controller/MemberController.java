package com.kzv.hellospring.controller;

import com.kzv.hellospring.domain.Member;
import com.kzv.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    /*
        필드주입 - 바꿔치기 불가능
        @Autowired
        private MemberService memberService;

        setter주입 - public으로 누구나 넣을 수 있게 열려있다는 문제점이 있음
        @Autowired
        public void setMemberService(MemberService memberService){
            this.memberService = memberService;
        }
     */
    //생성자 주입 - 의존관계가 실행중에 동적으로 바뀌는 경우가 없으므로 이 방법을 권장함.
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemeberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    //Controller 에서 생성된 데이터를 담아서 View 로 전달할 때 사용하는 객체.
    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";

    }
}

/*
    - 스프링 빈을 등록하는 2가지 방법 -
    컴포넌트 스캔과 자동 의존관계 설정: @Controller, @Service, @Repository 상위로 올라가면 @Component로 되어 있음

    자바 코드로 직접 스프린 빈 등록하는 방법
 */