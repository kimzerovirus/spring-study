package me.kzv.shopapi.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Getter
@Embeddable
public class Address {

    private String city;
    private String street;
    private String zipcode;

    protected Address(){} // JPA 는 protected 기본 생성자 까지 허용함.

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    /*
        JPA 는 엔티티나 임베디드 타입을 사용할 때 자바 기본 생성자를 public 또는 protected 로 설정해야 한다.
        이러한 제약을 두는 이유는 JPA 구현 라이브러리가 객체를 생성할 때 자바 리플랙션과 같은 기술을 사용할 수 있도록 지원해야 하기 때문이다.

        자바 리플렉션이란?
        구체적인 클래스 타입을 알지 못해도, 그 클래스의 메소드, 타입, 변수들에 접근할 수 있도록 해주는 자바 API 이다.

        자바 리플렉션 -> 자세한 내용은 https://1-7171771.tistory.com/123 참고
     */
}
