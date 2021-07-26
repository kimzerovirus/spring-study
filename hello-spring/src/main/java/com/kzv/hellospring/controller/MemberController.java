package com.kzv.hellospring.controller;

import com.kzv.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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


}

/*
    - 스프링 빈을 등록하는 2가지 방법 -
    컴포넌트 스캔과 자동 의존관계 설정: @Controller, @Service, @Repository 상위로 올라가면 @Component로 되어 있음

    자바 코드로 직접 스프린 빈 등록하는 방법
 */