package me.kzv.core.service;

import lombok.RequiredArgsConstructor;
import me.kzv.core.domain.Account;
import me.kzv.core.repository.UserRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class  UserService {

    private final UserRepository userRepository;

    public void createUser(Account account) {
        userRepository.save(account);
    }
}
