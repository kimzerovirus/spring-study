package me.kzv.hellospringboot;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

//@MyComponent // meta annotation
@RestController // dispatcher servlet 과는 관련 없음
@RequestMapping("/hello") // dispatcher servlet 에게 주소 정보를 넘김
public class HelloController implements ApplicationContextAware {
    private final HelloService helloService;
    private ApplicationContext applicationContext;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }


    @GetMapping // dispatcher servlet 에게 get 매핑이라는 정보를 넘겨준다. (name = ) 파라미터로 주소정보 넘겨도 됨
    @ResponseBody
    public String hello(String name) {
        if (name == null || name.trim().length() == 0) throw new IllegalArgumentException();
        return helloService.sayHello(Objects.requireNonNull(name));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println(applicationContext); // Root WebApplicationContext, started on Mon Feb 20 11:51:38 KST 2023 - 생성자 주입으로도 받아올 수 있음
        this.applicationContext = applicationContext;
    }

    @GetMapping("/count")
    public String count(String name) {
        return name + ": " + helloService.countOf(name);
    }
}
