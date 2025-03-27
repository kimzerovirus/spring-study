package me.kzv.jpabasic.controller.dto;

import lombok.Data;

@Data
public class MemberCreateReqDto {
    private String memberName;
    private Long teamId;
}
