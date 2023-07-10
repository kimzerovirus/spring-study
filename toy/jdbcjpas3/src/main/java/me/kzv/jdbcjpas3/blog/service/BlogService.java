package me.kzv.jdbcjpas3.blog.service;

import lombok.RequiredArgsConstructor;
import me.kzv.jdbcjpas3.blog.domain.Article;
import me.kzv.jdbcjpas3.blog.dto.AddArticleRequest;
import me.kzv.jdbcjpas3.blog.repository.BlogRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BlogService {
    private final BlogRepository blogRepository;

    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }
}
