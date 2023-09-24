package com.example.springmysql.web.application.usecase;

import com.example.springmysql.web.domain.follow.Follow;
import com.example.springmysql.web.domain.follow.FollowReadService;
import com.example.springmysql.web.domain.post.PostReadService;
import com.example.springmysql.web.domain.post.Timeline;
import com.example.springmysql.web.domain.post.TimelineReadService;
import com.example.springmysql.web.domain.post.dto.PostDto;
import com.example.springmysql.web.util.CursorRequest;
import com.example.springmysql.web.util.PageCursor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class GetTimelinePostsUsecase {
    final private FollowReadService followReadService;

    final private PostReadService postReadService;

    final private TimelineReadService timelineReadService;

    public PageCursor<PostDto> execute(Long memberId, CursorRequest cursorRequest) {
        var follows = followReadService.getFollowings(memberId);
        var followerMemberIds = follows
                .stream()
                .map(Follow::getToMemberId)
                .collect(Collectors.toList());

        return postReadService.getPostDtos(followerMemberIds, cursorRequest);
    }

    public PageCursor<PostDto> executeByTimeline(Long memberId, CursorRequest cursorRequest) {
        var pagedTimelines = timelineReadService.getTimelines(memberId, cursorRequest);
        var postIds = pagedTimelines.getBody().stream().map(Timeline::getPostId).collect(Collectors.toList());
        var posts = postReadService.getPostDtos(postIds);

        return new PageCursor<>(pagedTimelines.getNextCursorRequest(), posts);
    }
}

