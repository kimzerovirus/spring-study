package me.kzv.hellospringboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelloControllerTest {
    @Test
    public void helloController() throws Exception {
        HelloController helloController = new HelloController(name -> name);

        String test = helloController.hello("Test");
        Assertions.assertThat(test).isEqualTo("Test");
    }

    @Test
    public void failHelloController() throws Exception {
        HelloController helloController = new HelloController(name -> name);

        Assertions.assertThatThrownBy(() -> {
            helloController.hello(null);
        }).isInstanceOf(NullPointerException.class);

        Assertions.assertThatThrownBy(() -> {
            helloController.hello("");
        }).isInstanceOf(NullPointerException.class);
    }
}