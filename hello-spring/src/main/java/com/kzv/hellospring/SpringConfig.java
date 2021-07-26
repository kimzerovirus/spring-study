package com.kzv.hellospring;

import com.kzv.hellospring.repository.MemberRepository;
import com.kzv.hellospring.repository.MemoryMemberRepository;
import com.kzv.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
