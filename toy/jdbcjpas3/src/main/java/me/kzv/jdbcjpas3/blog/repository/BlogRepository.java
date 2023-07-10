package me.kzv.jdbcjpas3.blog.repository;

import me.kzv.jdbcjpas3.blog.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {
}
