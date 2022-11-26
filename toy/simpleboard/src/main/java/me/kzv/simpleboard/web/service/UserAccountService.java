package me.kzv.simpleboard.web.service;

import lombok.RequiredArgsConstructor;
import me.kzv.simpleboard.web.controller.dto.UserAccountDto;
import me.kzv.simpleboard.web.domain.UserAccount;
import me.kzv.simpleboard.web.repository.UserAccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserAccountService {
    private final UserAccountRepository userAccountRepository;

    @Transactional(readOnly = true)
    public Optional<UserAccountDto> searchUser(String username) {
        return userAccountRepository.findById(username)
                .map(UserAccountDto::from);
    }

    @Transactional
    public UserAccountDto saveUser(String username, String password, String email, String nickname, String memo) {
        return UserAccountDto.from(
                userAccountRepository.save(UserAccount.of(username, password, email, nickname, memo))
        );
    }
}
