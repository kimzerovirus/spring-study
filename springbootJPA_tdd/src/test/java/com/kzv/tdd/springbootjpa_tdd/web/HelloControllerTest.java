package com.kzv.tdd.springbootjpa_tdd.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void hello가_리턴된다()throws Exception{
        String hello = "hello";

        mvc.perform(MockMvcRequestBuilders.get("/hello"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(hello));
    }

}

/*
    WebMvcTest
     - 선언시 @Controller, @ControllerAdvice등을 사용할 수 있다.
     - @Service, @Component, @Repository 등은 사용 불가, 컨트롤러만 사용하기 때문에 선언한다.

    private MockMvc mvc
     - 웹 API HTTP, GET,POST등에 대한 테스트시 사용
     - 스프링 MVC테스트의 시작점이다.

    andException(status.isOk())
     - mvc.perform의 결과 검증
     - HTTP Header의 Status 검증 200, 404, 500 등
     - OK(200)을 검증한다.

    andException(content().string(hello))
     - Controller에서 "hello"를 리턴하는게 맞는 지 값을 검증한다.
 */