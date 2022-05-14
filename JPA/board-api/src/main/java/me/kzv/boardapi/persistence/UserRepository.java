package me.kzv.boardapi.persistence;

import me.kzv.boardapi.domain.User;
import me.kzv.boardapi.dto.SingleResultDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
}
