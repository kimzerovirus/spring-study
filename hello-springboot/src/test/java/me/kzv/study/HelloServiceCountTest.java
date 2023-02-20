package me.kzv.study;

import me.kzv.hellospringboot.HelloRepository;
import me.kzv.hellospringboot.HelloService;
import me.kzv.hellospringboot.HelloSpringBootTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.IntStream;

@HelloSpringBootTest
public class HelloServiceCountTest {
    @Autowired
    HelloService helloService;
    @Autowired
    HelloRepository helloRepository;

    @Test
    public void sayHelloIncreaseCount() throws Exception {
        IntStream.rangeClosed(1, 10).forEach(count -> {
            helloService.sayHello("Toby");
            Assertions.assertThat(helloRepository.countOf("Toby")).isEqualTo(count);
        });
    }
}
