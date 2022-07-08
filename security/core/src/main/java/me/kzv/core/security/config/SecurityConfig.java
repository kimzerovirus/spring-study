package me.kzv.core.security.config;

import lombok.RequiredArgsConstructor;
import me.kzv.core.security.common.FormAuthenticationDetailsSource;
import me.kzv.core.security.filter.ApiLoginProcessingFilter;
import me.kzv.core.security.handler.CustomAccessDeniedHandler;
import me.kzv.core.security.handler.CustomAuthenticationFailureHandler;
import me.kzv.core.security.handler.CustomAuthenticationSuccessHandler;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig {

//    private final CustomUserDetailsService customUserDetailsService;
//    private final CustomAuthenticationProvider customAuthenticationProvider;

    private final FormAuthenticationDetailsSource formAuthenticationDetailsSource;
    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        // 보안필터를 거치지 않는다.
        // 정적 파일 설정 /js /css /images
        return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        CustomAccessDeniedHandler accessDeniedHandler = new CustomAccessDeniedHandler();
        accessDeniedHandler.setErrorPage("/denied");
        return accessDeniedHandler;
    }

    @Bean
    public ApiLoginProcessingFilter apiLoginProcessingFilter(){
        ApiLoginProcessingFilter apiLoginProcessingFilter = new ApiLoginProcessingFilter();
//        apiLoginProcessingFilter.setAuthenticationManager();

        return apiLoginProcessingFilter;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http
                .authorizeRequests()
                .antMatchers("/", "/users","/login*").permitAll() // 보안필터를 거치지만 무조건 허용한다. login?error=true&exception=... -> /login*
                .antMatchers("/mypage").hasRole("USER")
                .antMatchers("/messages").hasRole("MANAGER")
                .antMatchers("/config").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login_proc")
                .authenticationDetailsSource(formAuthenticationDetailsSource)
                .defaultSuccessUrl("/", true)
                .successHandler(customAuthenticationSuccessHandler)
                .failureHandler(customAuthenticationFailureHandler)
                .permitAll()
        .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler())
        .and()
                .addFilterBefore(apiLoginProcessingFilter(), UsernamePasswordAuthenticationFilter.class) //api filter
//        .and()
//                .userDetailsService(customUserDetailsService)
//                .authenticationProvider(customAuthenticationProvider) // service를 포함하는 provider -> 따로 등록하지 않아도 알아서 설정해주는 것 같다.
        ;



        return http.build();
    }
}
