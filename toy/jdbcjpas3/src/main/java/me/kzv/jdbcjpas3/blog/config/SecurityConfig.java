//package me.kzv.jdbcjpas3.blog.config;
//
//import lombok.RequiredArgsConstructor;
//import me.kzv.jdbcjpas3.blog.service.UserDetailService;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;
//
//@RequiredArgsConstructor
//@Configuration
//public class SecurityConfig {
//    @Bean
//    public WebSecurityCustomizer configure() {
//        return web -> web.ignoring()
////                .requestMatchers(toH2Console())
//                .requestMatchers("/static/**");
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http
//                .authorizeHttpRequests(
//                        authorize -> authorize
//                                .requestMatchers("/login", "/signup", "/user").permitAll()
//                                .anyRequest().authenticated()
//                ) // 리다이렉션 에러가 난다면 그 페이지가 컨트롤러에 등록되어 있지 않거나 permitall 되지 않은 거임
//                .formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/articles"))
//                .logout(form -> form.logoutSuccessUrl("/login").invalidateHttpSession(true))
//                .csrf(AbstractHttpConfigurer::disable)
//                .build();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailService userDetailService) throws Exception {
//        return http.getSharedObject(AuthenticationManagerBuilder.class)
//                .userDetailsService(userDetailService)
//                .passwordEncoder(bCryptPasswordEncoder)
//                .and()
//                .build();
//    }
//
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
