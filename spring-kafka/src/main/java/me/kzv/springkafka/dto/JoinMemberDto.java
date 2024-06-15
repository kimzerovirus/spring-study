package me.kzv.springkafka.dto;

import lombok.Data;
import me.kzv.springkafka.domain.Member;

@Data
public class JoinMemberDto {
    private String memberName;
    private String organizationName;
    private String memo;

    public Member toMember(){
        return Member.builder()
                .memberName(memberName)
                .organizationName(organizationName)
                .memo(memo)
                .build();
    }
}
