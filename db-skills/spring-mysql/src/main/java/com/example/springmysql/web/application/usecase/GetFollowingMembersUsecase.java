package com.example.springmysql.web.application.usecase;

import com.example.springmysql.web.domain.follow.Follow;
import com.example.springmysql.web.domain.follow.FollowReadService;
import com.example.springmysql.web.domain.member.MemberReadService;
import com.example.springmysql.web.domain.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GetFollowingMembersUsecase {
    final private MemberReadService memberReadService;
    final private FollowReadService followReadService;

    public List<MemberDto> execute(Long memberId) {
        var followings = followReadService.getFollowings(memberId);
        var followingMemberIds = followings.stream().map(Follow::getToMemberId).collect(Collectors.toList());
        return memberReadService.getMembers(followingMemberIds);
    }
}
