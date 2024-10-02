package me.kzv.jpabasic.domain.member;

import me.kzv.jpabasic.domain.member.dto.MemberSearchDto;
import me.kzv.jpabasic.domain.member.dto.MemberWithTeamDto;
import org.springframework.data.domain.Page;

public interface MemberCustomRepository {
    Page<MemberWithTeamDto> searchMemberList(MemberSearchDto memberSearchDto);

    Page<MemberWithTeamDto> searchMemberListWithJdbcTemplate(MemberSearchDto memberSearchDto);
}
