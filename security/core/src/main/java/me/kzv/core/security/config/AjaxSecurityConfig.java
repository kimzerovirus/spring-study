package me.kzv.core.security.config;

import lombok.RequiredArgsConstructor;
import me.kzv.core.security.filter.AjaxLoginProcessingFilter;
import me.kzv.core.security.handler.AjaxAuthenticationFailureHandler;
import me.kzv.core.security.handler.AjaxAuthenticationSuccessHandler;
import me.kzv.core.security.provider.AjaxAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Order(1)
@Configuration
@RequiredArgsConstructor
public class AjaxSecurityConfig {

    private final AjaxAuthenticationFailureHandler ajaxAuthenticationFailureHandler;
    private final AjaxAuthenticationSuccessHandler ajaxAuthenticationSuccessHandler;
    private final AjaxAuthenticationProvider ajaxAuthenticationProvider;

    @Bean
    public SecurityFilterChain ajaxFilterChain(HttpSecurity http) throws Exception{
        http
                .antMatcher("/api/**")
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .addFilterBefore(ajaxLoginProcessingFilter(http), UsernamePasswordAuthenticationFilter.class)
                .csrf().disable()
                .authenticationProvider(ajaxAuthenticationProvider)

        ;

        return http.build();
    }

    @Bean
    public AjaxLoginProcessingFilter ajaxLoginProcessingFilter(HttpSecurity http) throws Exception {
        AjaxLoginProcessingFilter ajaxLoginProcessingFilter = new AjaxLoginProcessingFilter();
        ajaxLoginProcessingFilter.setAuthenticationManager(authenticationManagerBean(http));
        ajaxLoginProcessingFilter.setAuthenticationSuccessHandler(ajaxAuthenticationSuccessHandler);
        ajaxLoginProcessingFilter.setAuthenticationFailureHandler(ajaxAuthenticationFailureHandler);

        return ajaxLoginProcessingFilter;
    }

//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return authenticationManagerBean();
//    }

    @Bean
    // 원래는 WebSecurityConfigurerAdapter 상속 받으면 쓰던걸 global 로 쓸 수 있게 끄집어 왔다
    public AuthenticationManager authenticationManagerBean(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        return authenticationManagerBuilder.build();
    }

}
