package me.kzv.hellospringboot;

import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpHeaders.*;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.*;

@SpringBootApplication
public class HelloSpringbootApplication {

    public static void main(String[] args) {
        GenericApplicationContext applicationContext = new GenericApplicationContext();
        applicationContext.registerBean(HelloController.class);
        applicationContext.registerBean(SimpleHelloService.class);
        applicationContext.refresh();

//        SpringApplication.run(HelloSpringbootApplication.class, args);

//        new Tomcat().start(); TomcatServletWebServerFactory 가 알아서 설정함
        ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory(); // Jetty Netty 등을 집어넣으면 서버 교체 완료
        WebServer webServer = serverFactory.getWebServer(servletContext -> {
//            HelloController helloController = new HelloController(); // 스프링 컨테이너로 만듦

            servletContext.addServlet("frontcontroller", new HttpServlet() {
                @Override
                protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                    // 웹 3가지 요소 상태코드, 헤더, 바디
                    // 프론트 컨트롤러 - 인증, 보안, 다국어, 공통 기능

                    if (req.getRequestURI().equals("/hello") && req.getMethod().equals(GET.name())) {
                        String name = req.getParameter("name");

                        HelloController helloController = applicationContext.getBean(HelloController.class);
                        String result = helloController.hello(name);

                        resp.setStatus(OK.value());
//                        resp.setHeader(CONTENT_TYPE, TEXT_PLAIN_VALUE);
                        resp.setContentType(TEXT_PLAIN_VALUE);
                        resp.getWriter().println(result);
                    } else if (req.getRequestURI().equals("/user")) {
                        //
                    } else{
                        resp.setStatus(NOT_FOUND.value());
                    }
                }
            }).addMapping("/*");
        });
        webServer.start();
    }

}
