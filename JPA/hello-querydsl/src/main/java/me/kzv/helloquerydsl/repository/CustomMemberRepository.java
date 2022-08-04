package me.kzv.helloquerydsl.repository;

import me.kzv.helloquerydsl.dto.MemberSearchCondition;
import me.kzv.helloquerydsl.dto.MemberTeamDto;
import me.kzv.helloquerydsl.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomMemberRepository {
    List<MemberTeamDto> search(MemberSearchCondition condition);
    Page<MemberTeamDto> searchPageSimple(MemberSearchCondition condition, Pageable pageable);
    Page<MemberTeamDto> searchPageComplex(MemberSearchCondition condition, Pageable pageable);
}
