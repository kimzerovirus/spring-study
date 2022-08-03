package me.kzv.datajpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;
import java.util.UUID;

@EnableJpaAuditing
@SpringBootApplication
public class DataJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataJpaApplication.class, args);
    }

    @Bean
    public AuditorAware<String> auditorProvider(){

        // 시큐리티를 사용한다면 ContextHolder 에서 로그인 사용자를 꺼내오면 된다.
        return () -> Optional.of(UUID.randomUUID().toString());

        /*
                람다를 안쓰면
                return new AuditorAware<String>(){
                    @Override
                    public Optional<String> getCurrentAuditor() {
                        return Optional.of(UUID.randomUUID().toString());
                    }
                }
         */
    }
}
