package me.kzv.olle.account;

import me.kzv.olle.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly = true) // 성능의 이점?을 갖기 위해
public interface AccountRepository extends JpaRepository<Account, Long> {
    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);
}
