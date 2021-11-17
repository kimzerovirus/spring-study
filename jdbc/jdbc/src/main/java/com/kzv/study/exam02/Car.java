package com.kzv.study.exam02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Car {
    @Autowired // 알아서 Engine타입 주입해줘
    private Engine v8;

    public Car(){
        System.out.println("Car 생성자 생성");
    }

    public void run(){
        System.out.println("엔진을 이용하여 달립니다.");
        v8.exec();
    }

    // 자동차 클래스가 작동하려면 앤잔클래스가필요 -> 생성, 자동차가 만들어짐 -> 생성된 엔진을 넣어줌
//    public static void main(String[] args){
//        Engine e = new Engine();
//        Car c = new Car();
//        c.setEngine(e);
//        c.run();
//
//        //이러한 과정을 spring container가 하게 만들것이다. -> pom.xml의 property에 등록됨
//    }
}
