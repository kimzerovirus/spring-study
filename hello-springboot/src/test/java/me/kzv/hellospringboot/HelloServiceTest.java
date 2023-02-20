package me.kzv.hellospringboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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