package me.kzv.jdbcjpas3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Jdbcjpas3Application {

    public static void main(String[] args) {
        SpringApplication.run(Jdbcjpas3Application.class, args);
    }

}
