package me.kzv.jpabasic.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class MemberSearchDto {
    private String memberName;
    private String teamName;

    private int page;
    private int size;
}
