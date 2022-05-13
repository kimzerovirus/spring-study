package me.kzv.boardapi.persistence;

import me.kzv.boardapi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
