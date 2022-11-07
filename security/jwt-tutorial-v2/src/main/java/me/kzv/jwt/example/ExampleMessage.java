package me.kzv.jwt.example;

import lombok.Value;

@Value
public class ExampleMessage {
    String msg;
}

/**
 * 불변을 의미하는 어노테이션
 * @Value가 붙은 멤버필드는 private 접근제어자와 final 이 붙은 상수가 된다.(final 이 붙기 때문에 setter 는 존재할 수 없다.)
 * @Data - @Setter : @Getter + @Setter + @RequiredArgsConstructor + @ToString + @EqualsAndHashCode
 */