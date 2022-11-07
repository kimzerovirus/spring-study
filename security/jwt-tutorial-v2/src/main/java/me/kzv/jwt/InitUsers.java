package me.kzv.jwt;

import lombok.RequiredArgsConstructor;
import me.kzv.jwt.web.entity.JwtUser;
import me.kzv.jwt.web.entity.enums.Role;
import me.kzv.jwt.web.service.JwtUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class InitUsers implements CommandLineRunner {

    private final JwtUserService jwtUserService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (jwtUserService.findJwtUserByEmail("admin@test.com").isEmpty()) {
            JwtUser u = jwtUserService.save(JwtUser.builder()
                    .username("Admin")
                    .email("admin@test.com")
                    .password(passwordEncoder.encode("test123"))
                    .role(Set.of(Role.ROLE_ADMIN, Role.ROLE_USER))
                    .build());
            u.setEnabled(true);
            jwtUserService.save(u);
        }
        if (jwtUserService.findJwtUserByEmail("user@test.com").isEmpty()) {
            JwtUser u = jwtUserService.save(JwtUser.builder()
                    .username("User")
                    .email("user@test.com")
                    .password(passwordEncoder.encode("test123"))
                    .role(Set.of(Role.ROLE_USER))
                    .build());
            u.setEnabled(true);
            jwtUserService.save(u);
        }
    }

}