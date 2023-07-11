package me.kzv.jdbcjpas3.blog.repository;

import me.kzv.jdbcjpas3.blog.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
