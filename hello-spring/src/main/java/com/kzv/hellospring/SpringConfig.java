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

//    private DataSource dataSource;
//
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em){
        this.em = em;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }
        //jdbc
//        return new JdbcMemberRepository(dataSource);
        //jdbcTempalte
//        return new JdbcTemplateMemberRepository(dataSource);
        // JPA JPQL
        return new JpaMemberRepository(em);
    }
}

