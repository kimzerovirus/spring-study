package me.kzv.fullstackdev;

import me.kzv.fullstackdev.domain.User;
import me.kzv.fullstackdev.domain.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class FullstackDevApplication {

    @Transactional
    @Bean
    public CommandLineRunner run(UserRepository userRepository, PasswordEncoder passwordEncoder) throws Exception {
        return (String[] args) -> {
            User user = userRepository.findByUsername("user")
                    .orElseGet(
                            () -> User.builder()
                                    .username("user")
                                    .password(passwordEncoder.encode("1234"))
                                    .role("USER").build()
                    );

            userRepository.save(user);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(FullstackDevApplication.class, args);
    }

}
