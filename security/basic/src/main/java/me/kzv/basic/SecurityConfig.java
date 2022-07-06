package me.kzv.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withUsername("user")
                .password("{noop}1541") // spring 5.xx 부터는 패스워드 알고리즘을 prefix로 써준다.
                .roles("USER")
                .build();

        UserDetails sys = User.withUsername("sys")
                .password("{noop}1541")
                .roles("SYS","USER")
                .build();

        UserDetails admin = User.withUsername("admin")
                .password("{noop}1541")
                .roles("ADMIN","SYS","USER")
                .build();

        return new InMemoryUserDetailsManager(user, sys, admin);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        /*
        오버라이드 전에는 이렇게 설정되어 있으므로 스프링시큐리티를 의존성으로 등록했을때 기본 로그인 페이지가 뜨는것이다.
        http.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();

        계정 설정은 properties에 따로 설정해두었다.
        user 1541
         */

//        http
//                .authorizeRequests()
//                .anyRequest().authenticated()
//        ;

//        http
//                .antMatcher("/shop/**")
//                .authorizeRequests()
//                .antMatchers("/shop/login", "/shop/users/**").permitAll()
//                .antMatchers("/shop/mypage").hasRole("USER")
//                .antMatchers("/shop/admin/pay").access("hasRole('ADMIN')") // 먼저 실행해야 아래의 sys 권한 접근을 막을 수 있다.
//                .antMatchers("/shop/admin/**").access("hasRole('ADMIN') or hasRole('SYS')")
//                .anyRequest().authenticated()
//        ;

        http
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/user").hasRole("USER")
                .antMatchers("/admin/pay").hasRole("ADMIN") // 구체적인 범위가 아래의 넓은 범위 설정보다 항상 위에 있어야 한다.
                .antMatchers("/admin/**").access("hasRole('ADMIN') or hasRole('SYS')")
                .anyRequest().authenticated()
        ;

        http
                .formLogin()
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        RequestCache requestCache = new HttpSessionRequestCache();
                        SavedRequest savedRequest = requestCache.getRequest(request, response); // 사용자가 가고자하는 요청 정보
                        String redirectUrl = savedRequest.getRedirectUrl();
                        response.sendRedirect(redirectUrl);
                    }
                })
//                .loginPage("/loginPage")
                .defaultSuccessUrl("/")
                .failureUrl("/login")
                .usernameParameter("userId") // username default
                .passwordParameter("pwd") // password default
//                .loginProcessingUrl("/login_proc") // from action url /login default
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        System.out.println("authentication: " + authentication.getName());
                        response.sendRedirect("/");
                    }
                })
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                        System.out.println("exception " + exception.getMessage());
                        response.sendRedirect("/login");
                    }
                })
                .permitAll() // loginPage는 permitAll
        ;

        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .deleteCookies("JSESSIONID", "remember") // default remember-me
                .addLogoutHandler(new LogoutHandler() {
                    @Override
                    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
                        HttpSession session = request.getSession();
                        System.out.println(session);
                        session.invalidate();
                    }
                })
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        System.out.println("logout success!!!");
                        response.sendRedirect("/login");
                    }
                })
                .deleteCookies("remember")
        ;

        http.rememberMe()
                .rememberMeParameter("remember") // Default remember-me
                .tokenValiditySeconds(3600) // Default 14Days
//                .alwaysRemember(true) // 리멤버 미 기능이 활성화되지 않아도 항상 실행 여부 Default true
//                .userDetailsService(userDetailsService) // SecurityFilterChain에서는 자동으로 설정하는 것 같다.
        ;

        http.sessionManagement() // 세션 관리 기능
                .maximumSessions(1) // 최대 허용 가능 세션 수, -1 무제한 허용
                .maxSessionsPreventsLogin(true) // 동시로그인 차단 false: 기존 세션을 만료시킨다.(default) true: 현재 로그인 된 유저를 유지, 새로운 로그인은 차단 현재 유저 외의 더이상 세션을 생성하지 않는다
                .expiredUrl("/expired") // 세션이 만료된 경우 이동할 페이지
                .and()
                .invalidSessionUrl("/invalid") // 세션이 유효하지 않을 때 이동할 페이지
        ;

        http.sessionManagement()
                .sessionFixation().none() // changeSessionId (default), migrateSession, newSession ... 로그인 할 때 마다 새로운 세션아이디를 부여하여 쿠키를 이용한 공격을 차단하는게 바람직하다.
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // 스프링 시큐리티가 필요할 때 세션 생성default
                /*
                    SessionCreationPolicy.Always : 스프링시큐리티가 항상 세션 생성
                    SessionCreationPolicy.Never : 스프링 시큐리티가 세션을 생성하지 않지만, 세션이 이미 존재하면 사용한다.
                    SessionCreationPolicy.Stateless : 스프링시큐리티가 세션을 사용하지 않음 (이 방식이 세션을 완전히 사용하지 않는 JWT와 같은 방식에서 사용)
                 */
        ;

        http
                .exceptionHandling()
                .authenticationEntryPoint(new AuthenticationEntryPoint() {
                    @Override
                    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
                        System.out.println("인증 예외 발생!!!");
                        response.sendRedirect("/login"); // 시큐리티에서 제공하는 페이지가 아닌 우리가 만든 로그인페이지를 호출한다.
                    }
                })
                .accessDeniedHandler(new AccessDeniedHandler() {
                    @Override
                    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
                        System.out.println("인가 예외 발생!!!");
                        response.sendRedirect("/denied");
                    }
                });

        // SecurityContextHolder 모드 변경
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
        /*
                - MODE_THREADLOCAL : 쓰레드당 SecurityContext 객체를 할당 (default)
                - MODE_INHERITABLETHREADLOCAL : 메인 스레드와 자식 스레드가 동일한 SecurityContext를 유지
                - MODE_GLOBAL : 어플리케이션에서 단 하나의 SecurityContext를 저장한다.
         */
        return http.build();
    }


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/ignore1", "/ignore2");
    }

}
