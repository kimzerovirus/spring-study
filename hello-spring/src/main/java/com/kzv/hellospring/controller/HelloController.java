package com.kzv.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","hello!!");
        return "hello"; // templates의 hello.html찾아서 실행해라
    }
}

// 컨트롤러에서 리턴 값으로 문자를 반환하면 뷰 리졸버(viewResolver)가 화면을 찾아서 처리해준다.
// 스프링 부트 템플릿엔진 기본 viewName 매핑 => resources:templates/ + ViewFileName + .html

// spring-boot-devtools 라이브러리를 추가하면 html파일을 컴파일만 해주면 서버 재시작 없이 view 파일 변경이 가능하다.

/*
    1. ./gradlew build
    2. cd build/libs
    3. java -jar hello-spring-0.0.1-SNAPSHOT.jar
*/