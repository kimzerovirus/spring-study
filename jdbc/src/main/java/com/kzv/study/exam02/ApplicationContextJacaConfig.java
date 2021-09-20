package com.kzv.study.exam02;

import com.kzv.study.exam01.Car;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextJacaConfig {
    public static void main(String[] args){
        ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        Car car = (Car) ac.getBean(Car.class); // => Car car = (Car) ac.getBean("car"); 리턴 타입으로 등록할 수 있다.
        car.run();
    }
}
