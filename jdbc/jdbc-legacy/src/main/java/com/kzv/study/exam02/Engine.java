package com.kzv.study.exam02;

import org.springframework.stereotype.Component;

@Component
public class Engine {
    public Engine(){
        System.out.println("Engine 생성자");
    }

    public void exec(){
        System.out.println("엔진이 작동합니다.");
    }
}
