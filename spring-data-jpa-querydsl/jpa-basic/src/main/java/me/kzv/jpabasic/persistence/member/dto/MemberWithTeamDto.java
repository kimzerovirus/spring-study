package me.kzv.jpabasic.persistence.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class MemberWithTeamDto {
    private Long memberId;
    private String memberName;
    private Long teamId;
    private String teamName;
}
