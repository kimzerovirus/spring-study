package me.kzv.core.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        // 여러개의 PasswordEncoder 유형을 선언 후, 상황에 맞게 선택해서 사용할 수 있도록 지원하는 Encoder 이다. ldap MD4 MD5 PBKDF2 BCrypt ...
        // 암호화 포맷 {id}encodedPassword (default: Bcrypt)
        // ex) BCryptPasswordEncoder
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
