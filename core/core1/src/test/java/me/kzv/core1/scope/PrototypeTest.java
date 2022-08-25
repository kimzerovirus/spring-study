package me.kzv.core1.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class PrototypeTest {

    @Test
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        System.out.println("find prototypeBean1");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        System.out.println("find prototypeBean2");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);

        System.out.println("1 = " + prototypeBean1);
        System.out.println("2 = " + prototypeBean2);

        assertThat(prototypeBean1).isNotSameAs(prototypeBean2);
        ac.close();
    }

    @Scope("prototype")
    static class PrototypeBean {

        @PostConstruct
        public void init() {
            System.out.println("prototype init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("prototype destroy");
        }
    }
}

/**
 * 프로토타입 빈
 * - 스프링 컨테이너가 요청할 때 마다 새로 생성됨 (한상 새로운 객체 인스턴스가 생성된다)ㅠㅜ
 * - 스프링 컨테이너는 프로토타입 빈의 생성~의존관계 주입과 초기화까지만 관여함
 * - 종료 메서드가 호출되지 않음
 * - 따라서 프로토타입 빈은 클라이언트가 직접 관리해야 된다. ex) 종료 메서드 호출도 클라이언트가 직접...
 */
