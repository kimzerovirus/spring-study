package com.example.springmysql.web.application.usecase;

import com.example.springmysql.web.domain.follow.Follow;
import com.example.springmysql.web.domain.follow.FollowReadService;
import com.example.springmysql.web.domain.post.PostWriteService;
import com.example.springmysql.web.domain.post.TimelineWriteService;
import com.example.springmysql.web.domain.post.command.PostCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CreatePostUsecase {
    final private PostWriteService postWriteService;
    final private FollowReadService followReadService;
    final private TimelineWriteService timelineWriteService;

    @Transactional
    public Long execute(PostCommand command) {
        var postId = postWriteService.create(command);

        var followerMemberIds = followReadService
                .getFollowers(command.getMemberId()).stream()
                .map((Follow::getFromMemberId))
                .collect(Collectors.toList());

        timelineWriteService.deliveryToTimeLine(postId, followerMemberIds);

        return postId;
    }

}
