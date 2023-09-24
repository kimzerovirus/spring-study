package com.example.springmysql.web.domain.follow;

import com.example.springmysql.web.domain.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@RequiredArgsConstructor
@Service
public class FollowWriteService {
    final private FollowRepository followRepository;

    public Follow create(MemberDto fromMember, MemberDto toMember) {
        Assert.isTrue(!fromMember.getId().equals(toMember.getId()), "From, To 회원이 동일합니다.");

        var follow = Follow
                .builder()
                .fromMemberId(fromMember.getId())
                .toMemberId(toMember.getId())
                .build();
        return followRepository.save(follow);
    }
}

