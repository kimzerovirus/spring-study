package me.kzv.helloquerydsl.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberDto {

    private String username;
    private int age;

    @QueryProjection // dto 도 Q 클래스로 생성이 가능하다. 선언 후 빌드는 필수
    public MemberDto(String username, int age) {
        this.username = username;
        this.age = age;
    }
}
