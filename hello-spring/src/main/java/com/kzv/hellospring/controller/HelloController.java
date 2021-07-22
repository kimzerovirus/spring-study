package com.kzv.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello"; // templates의 hello.html찾아서 실행해라
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-mvc"; //http://localhost:8088/hello-mvc?name=spring!!!!!!
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name, Model model) {
        return "hello " + name; //api
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello(); //json방식으로 넘어감 { name:값 }, json반환이 기본임
        hello.setName(name);
        return hello;
    }

    static class Hello{ //static class는 class안에서 쓸 수 있음
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}

// 컨트롤러에서 리턴 값으로 문자를 반환하면 뷰 리졸버(viewResolver)가 화면을 찾아서 처리해준다.
// 스프링 부트 템플릿엔진 기본 viewName 매핑 => resources:templates/ + ViewFileName + .html

// spring-boot-devtools 라이브러리를 추가하면 html파일을 컴파일만 해주면 서버 재시작 없이 view 파일 변경이 가능하다.

/*
    1. ./gradlew build
    2. cd build/libs
    3. java -jar hello-spring-0.0.1-SNAPSHOT.jar

    model view controller
*/

/*
    @ResponseBody
    HttpMessageConverter
    단순string이면 -> StringConverter
    객체이면 JsonConverter가 작동 (스프링은 MappingJackson2HttpMessageConverter)
 */

/*
    컨트롤러 : 웹 MVC의 컨트롤러 역할
    서비스 : 핵심 비즈니스 로직 구현
    리포지토리 : 데이터베이스에 접근, 도메인 객체를 DB에 저장하고 관리
    도메인 : 비즈니스 도메인 객체, 예) 회원, 주문, 쿠폰, 등등 주로 데이터베이스에 저장하고 관리됨
 */