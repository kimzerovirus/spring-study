package me.kzv.jdbcjpas3.blog.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter // configurationProperties 는 매핑할 때 setter를 사용하나봄
@Component
@ConfigurationProperties("jwt")
public class JwtProperties {
    private String issuer;
    private String secretKey;
}
