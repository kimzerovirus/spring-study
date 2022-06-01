package com.kzv.study.exam01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextExam01 {
    public static void main(String[] args){
        // Bean정보를 읽어드려 공장 만들기
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        System.out.println("초기화 완료!");

        UserBean userBean = (UserBean) ac.getBean("userBean"); // getbean의 파라미터로 xml의 id값을 넣어주면 해당 객체의 레퍼런스가 반환된다. Object타입을 반환하여 타입을 변환해줘야한다.
        userBean.setName("kang");

        System.out.println(userBean.getName());

        UserBean userBean2 = (UserBean) ac.getBean("userBean");
        if(userBean == userBean2){
            System.out.println("같은 인스턴스입니다."); //싱글턴 패턴으로 빈 객체를 생성해주므로 처음과 나중에 생성된 객체가 같은 객체인 것이다.
        }

    }
}
