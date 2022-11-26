package me.kzv.simpleboard.web.repository;

import me.kzv.simpleboard.web.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, String> {
}