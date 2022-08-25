package me.kzv.core1.scope;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        Assertions.assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        Assertions.assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }

    @Test
    void singletonClientUsePrototype(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        Assertions.assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();

        Assertions.assertThat(count2).isEqualTo(2);

    }

    @Scope("singleton")
    @RequiredArgsConstructor
    static class ClientBean {
        private final PrototypeBean prototypeBean; // 싱글톤으로 감싸져 있기 때문에 생성시점에 주입되어 계속 사용하게 됨... 두번째 호출해도 새로운 인스턴스를 만들지 않고 처음 생성한 녀석을 보내줌

        public int logic(){
            // 무식한 방법으로 ApplicationContext 를 직접 주입 받고 여기서 직접 getBean 해서 꺼내오면 원하는대로 항상 새로운 인스턴스를 만들어 낼 수 있다.
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            System.out.println("logic = " + count);
            return count;
        }
    }

    @Test
    void singletonClientUsePrototypeSolved(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBeanSolved.class, PrototypeBean.class);
        ClientBeanSolved clientBean1 = ac.getBean(ClientBeanSolved.class);
        int count1 = clientBean1.logic();
        Assertions.assertThat(count1).isEqualTo(1);

        ClientBeanSolved clientBean2 = ac.getBean(ClientBeanSolved.class);
        int count2 = clientBean2.logic();

        Assertions.assertThat(count2).isEqualTo(1);
    }

    /**
     * 지정한 빈을 컨테이너에서 대신 찾아주는 DL (Dependency Lookup) 서비스를 제공하는 ObjectProvider ... 과거에는 ObjectFactory 였지만 기능이 추가되며 ObjectProvider 를 사용하게 되었다.
     * 다만 스프링에 의존을 한다는 단점이 존재
     */
    @Scope("singleton")
    @RequiredArgsConstructor
    static class ClientBeanSolved {
        private final ObjectProvider<PrototypeBean> prototypeBeanProvider;

        public int logic(){
            PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            System.out.println("logic = " + count);
            return count;
        }
    }

    @Test
    void singletonClientUsePrototypeSolved2(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBeanSolved2.class, PrototypeBean.class);
        ClientBeanSolved2 clientBean1 = ac.getBean(ClientBeanSolved2.class);
        int count1 = clientBean1.logic();
        Assertions.assertThat(count1).isEqualTo(1);

        ClientBeanSolved2 clientBean2 = ac.getBean(ClientBeanSolved2.class);
        int count2 = clientBean2.logic();

        Assertions.assertThat(count2).isEqualTo(1);
    }

    // 순수자바만 사용하는 법 javax.inject 모듈 추가해줘야함
    // get 하나만 제공하므로 단순하다.
    @Scope("singleton")
    @RequiredArgsConstructor
    static class ClientBeanSolved2 {
        private final Provider<PrototypeBean> prototypeBeanProvider; // javax.inject 의 Provider

        public int logic(){
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            System.out.println("logic = " + count);
            return count;
        }
    }

    @Scope("prototype")
    static class PrototypeBean{
        private int count = 0;

        public void addCount(){
            count++;
        }

        public int getCount(){
            return count;
        }

        @PostConstruct
        public void init(){
            System.out.println("count = " + count);
            System.out.println("PrototypeBean.init = "+ this);
        }

        @PreDestroy
        public void destroy(){
            System.out.println("호출 안 됨");
        }
    }
}
