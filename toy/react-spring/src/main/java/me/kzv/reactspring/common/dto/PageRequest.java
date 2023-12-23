package me.kzv.reactspring.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder // builder 와 달리 상속된 필드까지 빌더를 생성해준다.
@NoArgsConstructor
@AllArgsConstructor
public class PageRequest {

    // 빌더 패턴을 사용하여 인스턴스를 만들 때 특정값으로 초기화하고 싶다면 default 를 사용하면 된다.
    @Builder.Default
    private int page = 1;

    @Builder.Default
    private int size = 10;
}
