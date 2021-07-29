package com.kzv.hellospring;

import com.kzv.hellospring.repository.*;
import com.kzv.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;


@Configuration
public class SpringConfig {

    //JDBC
//    private DataSource dataSource;
//
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    //JPA
//    private EntityManager em;
//
//    @Autowired
//    public SpringConfig(EntityManager em){
//        this.em = em;
//    }

    //SpringData JPA
    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    //JDBC
//    @Bean
//    public MemberService memberService() {
//        return new MemberService(memberRepository());
//    }

    //SpringData JPA
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

//    @Bean
//    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }
        //jdbc
//        return new JdbcMemberRepository(dataSource);
        //jdbcTempalte
//        return new JdbcTemplateMemberRepository(dataSource);
        // JPA JPQL
//        return new JpaMemberRepository(em);

//    }
}

