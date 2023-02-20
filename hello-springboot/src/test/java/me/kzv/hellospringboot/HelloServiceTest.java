package me.kzv.hellospringboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.junit.jupiter.api.Assertions.*;

//@Retention(RetentionPolicy.RUNTIME)
//@Target(ElementType.METHOD)
//@Test
//@interface UnitTest {
//
//}

class HelloServiceTest {

    @Test
    public void simpleHelloService() throws Exception {
        SimpleHelloService helloService = new SimpleHelloService(helloRepositoryMock); // 필요 없는 의존이므로 mock 을 만들어 넣어줌

        String rest = helloService.sayHello("Test");

        Assertions.assertThat(rest).isEqualTo("Hello Test");
    }


    @Test
    public void helloDecorator() throws Exception {
        HelloDecorator helloService = new HelloDecorator(name -> name);

        String rest = helloService.sayHello("Test");

        Assertions.assertThat(rest).isEqualTo("*Test*");
    }

    private static HelloRepository helloRepositoryMock = new HelloRepository() {
        @Override
        public Hello findHello(String name) {
            return null;
        }

        @Override
        public void increaseCount(String name) {

        }
    };
}