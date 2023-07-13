package me.kzv.jdbcjpas3.blog.service;

import lombok.RequiredArgsConstructor;
import me.kzv.jdbcjpas3.blog.domain.Article;
import me.kzv.jdbcjpas3.blog.dto.AddArticleRequest;
import me.kzv.jdbcjpas3.blog.dto.UpdateArticleRequest;
import me.kzv.jdbcjpas3.blog.repository.BlogRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BlogService {
    private final BlogRepository blogRepository;

    @Transactional
    public Article save(AddArticleRequest request, String author) {
        return blogRepository.save(request.toEntity(author));
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
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));

        authorizeArticleAuthor(article);
        blogRepository.delete(article);
    }


    @Transactional
    public Article update(long id, UpdateArticleRequest request) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));

        authorizeArticleAuthor(article);
        article.update(request.getTitle(), request.getContent());

        return article;
    }

    // 게시글을 작성한 유저인지 확인
    private static void authorizeArticleAuthor(Article article) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!article.getAuthor().equals(userName)) {
            throw new IllegalArgumentException("not authorized");
        }
    }
}
