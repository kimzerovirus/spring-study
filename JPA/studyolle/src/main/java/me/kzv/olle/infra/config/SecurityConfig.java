package me.kzv.olle.infra.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // 이거 안해주면 post 요청에 csrf 토큰이 없으므로 403에러가 난다.
                .headers().frameOptions().disable()

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                .antMatchers("/", "/api/v1/account/**").permitAll()
//                .mvcMatchers(HttpMethod.GET,"/api/v1/profile/*").permitAll()
                .anyRequest().authenticated()
        ;
    }

    @Override
    public void configure(WebSecurity webSecurity) throws Exception{
        webSecurity.ignoring()
                // static 자원들은 시큐리티를 무시하도록 설정
                .antMatchers("/h2-console/**","/favicon.ico")
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }
}
