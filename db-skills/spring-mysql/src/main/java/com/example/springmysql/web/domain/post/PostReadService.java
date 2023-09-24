package com.example.springmysql.web.domain.post;

import com.example.springmysql.web.domain.post.dto.DailyPostCount;
import com.example.springmysql.web.domain.post.dto.DailyPostCountRequest;
import com.example.springmysql.web.domain.post.dto.PostDto;
import com.example.springmysql.web.util.CursorRequest;
import com.example.springmysql.web.util.PageCursor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostReadService {
    final private PostRepository postRepository;

    final private PostLikeRepository postLikeRepository;

    public List<Post> getPosts(Long memberId) {
        return postRepository.findByMemberId(memberId);
    }

    public Post getPost(Long postId) {
        return postRepository.findById(postId, false).orElseThrow();
    }

    public List<PostDto> getPostDtos(List<Long> postIds) {
        return postRepository.findAllByIdIn(postIds).stream().map(this::toDto).collect(Collectors.toList());
    }

    public PageCursor<PostDto> getPostDtos(List<Long> memberIds, CursorRequest cursorRequest) {
        var posts = findAllBy(memberIds, cursorRequest);
        long nextKey = getNextKey(posts);
        var postDtos = posts.stream().map(this::toDto).collect(Collectors.toList());
        return new PageCursor<>(cursorRequest.next(nextKey), postDtos);
    }
    private PostDto toDto(Post post) {
        return new PostDto(
                post.getId(),
                post.getMemberId(),
                post.getContents(),
                post.getCreatedAt(),
                postLikeRepository.countByPostId(post.getId())
        );
    }

    public List<DailyPostCount> getDailyPostCounts(DailyPostCountRequest request) {
        return postRepository.groupByCreatedDate(request);
    }

    public Page<PostDto> getPostDtos(Long memberId, PageRequest pageRequest) {
        return postRepository.findAllByMemberId(memberId, pageRequest).map(this::toDto);
    }

    public Page<Post> getPost(Long memberId, PageRequest pageRequest) {
        return postRepository.findAllByMemberId(memberId, pageRequest);
    }

    public PageCursor<Post> getPosts(Long memberId, CursorRequest cursorRequest) {
        var posts = findAllBy(memberId, cursorRequest);
        long nextKey = getNextKey(posts);
        return new PageCursor<>(cursorRequest.next(nextKey), posts);
    }

    private long getNextKey(List<Post> posts) {
        return posts.stream()
                .mapToLong(Post::getId)
                .min()
                .orElse(CursorRequest.NONE_KEY);
    }

    private List<Post> findAllBy(Long memberId, CursorRequest cursorRequest) {
        if (cursorRequest.hasKey()) {
            return postRepository.findAllByLessThanIdAndMemberIdAndOrderByIdDesc(
                    cursorRequest.getKey(),
                    memberId,
                    cursorRequest.getSize()
            );
        }

        return postRepository.findAllByMemberIdAndOrderByIdDesc(memberId, cursorRequest.getSize());
    }

    private List<Post> findAllBy(List<Long> memberIds, CursorRequest cursorRequest) {
        if (cursorRequest.hasKey()) {
            return postRepository.findAllByLessThanIdAndMemberIdInAndOrderByIdDesc(
                    cursorRequest.getKey(),
                    memberIds,
                    cursorRequest.getSize()
            );
        }

        return postRepository.findAllByMemberIdInAndOrderByIdDesc(memberIds, cursorRequest.getSize());
    }

}

