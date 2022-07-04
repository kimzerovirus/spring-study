package me.kzv.demo.config;

import lombok.extern.log4j.Log4j2;
import me.kzv.demo.security.filter.ApiCheckFilter;
import me.kzv.demo.security.filter.ApiLoginFilter;
import me.kzv.demo.security.handler.ApiLoginFailHandler;
import me.kzv.demo.security.handler.ClubLoginSuccessHandler;
import me.kzv.demo.security.util.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Log4j2
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true) // authorizeRequests를 어노테이션으로 설정할 수 있게 해준다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ClubLoginSuccessHandler successHandler(){
        return new ClubLoginSuccessHandler(passwordEncoder());
    }

    @Bean
    public ApiCheckFilter apiCheckFilter(){
        return new ApiCheckFilter("/notes/**/*", jwtUtil());
    }

    @Bean
    public JwtUtil jwtUtil(){
        return new JwtUtil();
    }

    @Bean
    public ApiLoginFilter apiLoginFilter() throws Exception {
        ApiLoginFilter apiLoginFilter = new ApiLoginFilter("/api/login", jwtUtil());
        apiLoginFilter.setAuthenticationManager(authenticationManager());
        // if fail to login
        apiLoginFilter.setAuthenticationFailureHandler(new ApiLoginFailHandler());

        return apiLoginFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.authorizeRequests()
                .antMatchers("/sample/test").permitAll()
                .antMatchers("/sample/member").hasRole("USER");

        http.formLogin().defaultSuccessUrl("http://localhost:8080/sample/member"); // 인가/인증에 문제시 로그인 화면
        // http.formLogin().loginPage(); loginProcessURl(), defaultSuccessUrl() failureUrl()등으로 커스텀 페이지로 이동 가능

        http.csrf().disable(); // 일반적으로 세션방식에서는 csrf 토큰이 보안상으로 권장되지만 REST 방식에서는 csrf토큰 값을 항상 알아야되서 불편하므로 사용하지 않는 경우가 많음
        http.logout(); // csrf 토큰을 이용하는 경우 "/logout" url 호출시 로그아웃 버튼이 있는 post방식의 form 제출 화면이 등장한다. disable()상태에서는 get방식으로 로그아웃 가능
        // http.logout().logoutUrl()로그아웃url logoutSuccessUrl()로그아웃 성공 후 이동url 등의 커스텀 메서드 존재

        http.oauth2Login().successHandler(successHandler());
        http.rememberMe().tokenValiditySeconds(60 * 60 * 7).userDetailsService(userDetailsService()); // 7days ... remember-me 라는 쿠키가 생성된다.

        // UsernamePasswordAuthenticationFilter는 사용자의 아이디와 패스워드를 기반으로 동작한다.
        // UsernamePasswordAuthenticationFilter가 작동하기 전에 apiCheckFilter가 작동하게 설정
        http.addFilterBefore(apiCheckFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(apiLoginFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    // TEST
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//        auth.inMemoryAuthentication().withUser("user1")
//                .password("$2a$10$9SzxzIKCei8iODizhZKIaehFNqlmXixL2v6xp7UWkV6JUWtwxVtjS") // 1111 인코딩 결과
//                .roles("USER");
//    }
}
