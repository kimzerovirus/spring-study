package com.kzv.tdd.springbootjpa_tdd.web.service.posts;

import com.kzv.tdd.springbootjpa_tdd.web.domain.posts.Posts;
import com.kzv.tdd.springbootjpa_tdd.web.domain.posts.PostsRepository;
import com.kzv.tdd.springbootjpa_tdd.web.dto.PostsListResponseDto;
import com.kzv.tdd.springbootjpa_tdd.web.dto.PostsResponseDto;
import com.kzv.tdd.springbootjpa_tdd.web.dto.PostsSaveRequestDto;
import com.kzv.tdd.springbootjpa_tdd.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        // JPA에서는 트랜잭션이 끝나는 시점에 변화가 있는 모든 엔티티 객체를 데이터베이스에 자동으로 반영
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new) // .map(posts -> new PostsListResponseDto(posts)) 다음과 같이 람다로 작성가능하다.
                .collect(Collectors.toList());

        //postsRepository 결과로 넘어온 Posts의 STream을 map을 통해 PostsListResponseDto로 변화 -> List로 반환하는 메소드이다.
    }

    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id= " + id));

        postsRepository.delete(posts);
    }
}
