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
        SimpleHelloService helloService = new SimpleHelloService();

        String rest = helloService.sayHello("Test");

        Assertions.assertThat(rest).isEqualTo("Hello Test");
    }

    @Test
    public void helloDecorator() throws Exception {
        HelloDecorator helloService = new HelloDecorator(name -> name);

        String rest = helloService.sayHello("Test");

        Assertions.assertThat(rest).isEqualTo("*Test*");
    }
}