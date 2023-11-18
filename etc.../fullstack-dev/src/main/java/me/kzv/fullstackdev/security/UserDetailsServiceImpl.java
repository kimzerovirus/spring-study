package me.kzv.fullstackdev.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.kzv.fullstackdev.domain.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var currentUser = userRepository.findByUsername(username).orElseThrow(() -> {
            throw new UsernameNotFoundException("User not found");
        });

        User.UserBuilder userBuilder = User.withUsername(username);
        userBuilder.password(currentUser.getPassword());
        userBuilder.roles(currentUser.getRole());

        log.info("current user: {}", username);

        return userBuilder.build();
    }
}
