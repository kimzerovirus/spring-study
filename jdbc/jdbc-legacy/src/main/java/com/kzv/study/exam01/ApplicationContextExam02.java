package com.kzv.study.exam01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextExam02 {
    public static void main(String[] args){
        // Bean정보를 읽어드려 공장 만들기
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

        Car car = (Car)ac.getBean("c"); //엔진을 탑재한 자동차 객체가 생성된다.
        car.run();
        // 이렇게 어떤 객체에게 어떤 객체를 주입하는 것을 DI(dependency inject)라고 한다.
    }
}
