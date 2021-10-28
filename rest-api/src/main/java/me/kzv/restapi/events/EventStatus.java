package me.kzv.restapi.events;

public enum EventStatus {
    DRAFT, PUBLISHED, BEGAN_ENROLLEMENT
}

/*
http://tcpschool.com/java/java_api_enum
자바의 열거체는 다음과 같은 장점을 가집니다.

1. 열거체를 비교할 때 실제 값뿐만 아니라 타입까지도 체크합니다.

2. 열거체의 상숫값이 재정의되더라도 다시 컴파일할 필요가 없습니다.
 */