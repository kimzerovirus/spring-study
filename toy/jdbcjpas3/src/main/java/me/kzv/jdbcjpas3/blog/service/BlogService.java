package me.kzv.jdbcjpas3.blog.service;

import lombok.RequiredArgsConstructor;
import me.kzv.jdbcjpas3.blog.domain.Article;
import me.kzv.jdbcjpas3.blog.dto.AddArticleRequest;
import me.kzv.jdbcjpas3.blog.dto.UpdateArticleRequest;
import me.kzv.jdbcjpas3.blog.repository.BlogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BlogService {
    private final BlogRepository blogRepository;

    @Transactional
    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }

    @Transactional(readOnly = true)
    public List<Article> findAll(){
        return blogRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Article findById(long id) {
        return blogRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found: " + id));
    }

    @Transactional
    public void delete(long id) {
        blogRepository.deleteById(id);
    }

    @Transactional
    public Article update(long id, UpdateArticleRequest request) {
        Article article = blogRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found: " + id));
        article.update(request.getTitle(), request.getContent());

        return article;
    }
}
