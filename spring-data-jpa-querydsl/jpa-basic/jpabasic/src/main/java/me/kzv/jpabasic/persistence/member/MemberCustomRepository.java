package me.kzv.jpabasic.persistence.member;

import me.kzv.jpabasic.persistence.member.dto.MemberSearchDto;
import me.kzv.jpabasic.persistence.member.dto.MemberWithTeamDto;
import org.springframework.data.domain.Page;

public interface MemberCustomRepository {
    Page<MemberWithTeamDto> searchMemberList(MemberSearchDto memberSearchDto);
}
