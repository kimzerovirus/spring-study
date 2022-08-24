package me.kzv.core1.singleton;

import me.kzv.core1.spring.AppConfig;
import me.kzv.core1.spring.member.MemberRepository;
import me.kzv.core1.spring.member.MemberServiceImpl;
import me.kzv.core1.spring.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class ConfigurationSingletonTest {

    @Test
    public void configurationTest() throws Exception {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);

        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("mb => mr : " + memberRepository1);
        System.out.println("os => mr : " + memberRepository2);
        System.out.println("memberRepository : " + memberRepository);

        // 세놈 다 같은 놈
        Assertions.assertThat(memberRepository1).isSameAs(memberRepository2);
    }

    @Test
    void configurationDeep(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean = " + bean.getClass());

        /*
            bean = class me.kzv.core1.spring.AppConfig$$EnhancerBySpringCGLIB$$31439d8d

            내가 만든 클래스가 아닌 스프링이 CGLIB 라는 바이트코드 조작 라이브러리를 사용해서 AppConfig 클래스를 상속받은 임의의 다른 클래스를 만들고,
            그 클래스를 스프링 빈으로 등록한다.

            그리고 이 임의의 코드가 싱글톤을 보장해준다.

            AppConfig@CGLIB 예상

            @Bean
            public MemberRepository memberRepository(){
                if(memoryMemberRepository 가 스프링 컨테이너에 등록되어 있으면?){
                    return 스프링 컨테이너에서 찾아서 반환

                } else { 스프링 컨테이너에 없는 경우
                    기존 로직을 호출해서 MemberRepository 를 생성하고 스프링 컨테이너에 등록
                    return 반환
                }
             }

             [참고] AppConfig@CGLIB 는 AppConfig 의 자식타입이므로, AppConfig 타입으로 조회 가능하다. -> 자식은 부모로 조회시 다 끌려온다.

             ** configure 어노테이션을 빼고 bean 만 등록하면 싱글톤이 보장되지 않는다!!! ** - CGLIB 를 사용하지 않아 내가 만든 클래스가 나올것이다.
         */
    }
}
