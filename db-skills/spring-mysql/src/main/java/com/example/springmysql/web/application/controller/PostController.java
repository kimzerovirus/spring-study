package com.example.springmysql.web.application.controller;

import com.example.springmysql.web.application.usecase.CreatePostLikeUsecase;
import com.example.springmysql.web.application.usecase.CreatePostUsecase;
import com.example.springmysql.web.application.usecase.GetTimelinePostsUsecase;
import com.example.springmysql.web.domain.post.PostReadService;
import com.example.springmysql.web.domain.post.PostWriteService;
import com.example.springmysql.web.domain.post.command.PostCommand;
import com.example.springmysql.web.domain.post.dto.DailyPostCount;
import com.example.springmysql.web.domain.post.dto.DailyPostCountRequest;
import com.example.springmysql.web.domain.post.dto.PostDto;
import com.example.springmysql.web.util.CursorRequest;
import com.example.springmysql.web.util.PageCursor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostController {
    final private PostWriteService postWriteService;
    final private PostReadService postReadService;
    final private GetTimelinePostsUsecase getTimelinePostsUsecase;
    final private CreatePostUsecase createPostUsecase;
    final private CreatePostLikeUsecase createPostLikeUsacase;

    @PostMapping("")
    public Long create(@RequestBody PostCommand command) {
//        return postWriteService.create(command);
        return createPostUsecase.execute(command);
    }

    @GetMapping("/daily-post-counts")
    public List<DailyPostCount> getDailyPostCounts(DailyPostCountRequest request) {
        return postReadService.getDailyPostCounts(request);
    }


    @GetMapping("/members/{memberId}")
    public Page<PostDto> getPosts(
            @PathVariable Long memberId,
            @RequestParam Integer page,
            @RequestParam Integer size
    ) {
        return postReadService.getPostDtos(memberId, PageRequest.of(page, size));
    }


    @GetMapping("/members/{memberId}/timeline")
    public PageCursor<PostDto> getTimeline(
            @PathVariable Long memberId,
            CursorRequest cursorRequest
    ) {
//        return getTimelinePostsUsecase.execute(memberId, cursorRequest);
        return getTimelinePostsUsecase.executeByTimeline(memberId, cursorRequest);
    }


    @PostMapping("/{postId}/like/v1")
    public void like(@PathVariable Long postId) {
//        postWriteService.likePost(postId);
        postWriteService.likePostByOptimisticLock(postId);
    }

    @PostMapping("/{postId}/like/v2")
    public void like(
            @PathVariable Long postId,
            @RequestParam Long memberId
    ) {
        createPostLikeUsacase.execute(postId, memberId);
    }

}
