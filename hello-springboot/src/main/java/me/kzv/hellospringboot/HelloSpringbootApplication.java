package me.kzv.hellospringboot;

import me.kzv.config.MySpringBootApplication;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

//@SpringBootApplication
@MySpringBootApplication
public class HelloSpringbootApplication {
    //팩토리 메서드 -> Component Scan 이용하는 방식으로 변경
//    @Bean
//    public HelloController helloController(HelloService helloService){
//        return new HelloController(helloService);
//    }
//
//    @Bean
//    public HelloService helloService(){
//        return new SimpleHelloService();
//    }

//    @Bean
//    public ServletWebServerFactory servletWebServerFactory() {
//        return new TomcatServletWebServerFactory();
//    }
//
//    @Bean
//    public DispatcherServlet dispatcherServlet() {
//        return new DispatcherServlet();
//    }


    @Bean
    ApplicationRunner applicationRunner(Environment env){
        return args -> {
            String name = env.getProperty("my.name");
            System.out.println("my.name: " + name);
        };
    }

    public static void main(String[] args) {
//        MySpringApplication.run(HelloSpringbootApplication.class, args); // spring boot 와 똑같은 설계
        SpringApplication.run(HelloSpringbootApplication.class, args);
    }
}
