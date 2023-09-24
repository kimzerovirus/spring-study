package com.example.springmysql.web.application.usecase;

import com.example.springmysql.web.domain.member.MemberReadService;
import com.example.springmysql.web.domain.member.dto.MemberDto;
import com.example.springmysql.web.domain.post.Post;
import com.example.springmysql.web.domain.post.PostLikeWriteService;
import com.example.springmysql.web.domain.post.PostReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreatePostLikeUsecase {
    final private PostReadService postReadService;
    final private MemberReadService memberReadService;
    final private PostLikeWriteService postLikeWriteService;

    public void execute(Long postId, Long memberId) {
        Post post = postReadService.getPost(postId);
        MemberDto member = memberReadService.getMember(memberId);

        postLikeWriteService.create(post, member);
    }
}
