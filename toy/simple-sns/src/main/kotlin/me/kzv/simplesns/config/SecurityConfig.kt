package me.kzv.simplesns.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain

// https://docs.spring.io/spring-security/reference/servlet/authorization/authorize-http-requests.html

@EnableWebSecurity
@Configuration
class SecurityConfig {

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests { authorize ->
                authorize
                    .requestMatchers("/api/*/users/join", "/api/*/users/login").permitAll()
                    .anyRequest().authenticated()
            }
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()


        return http.build()
    }

}