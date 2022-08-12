package me.kzv.boardapi.security;

import lombok.RequiredArgsConstructor;
import me.kzv.boardapi.common.exception.CustomUserNotFoundException;
import me.kzv.boardapi.web.persistence.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userPk) {
        return userRepository.findById(Long.valueOf(userPk)).orElseThrow(CustomUserNotFoundException::new);
    }
}
