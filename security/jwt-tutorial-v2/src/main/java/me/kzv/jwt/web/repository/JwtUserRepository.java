package me.kzv.jwt.web.repository;

import me.kzv.jwt.web.entity.JwtUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JwtUserRepository extends JpaRepository<JwtUser, Long> {

    Optional<JwtUser> findJwtUserByUsername(String username);
    Optional<JwtUser> findJwtUserByEmail(String email);
}
