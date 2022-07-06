# Spring Security Basic
> 스프링 시큐리티 주요 아키텍처의 이해 파트는 여러번 반복해서 볼것

 ## HttpSecurity
- Authentication
    - formLogin()
    - logout()
    - csrf()
    - httpBasic()
    - SessionManagement()
    - RememberMe()
    - ExceptionHandling()
    - addFilter()
- Authorization
    - authorizeRequests()
    - antMatchers(/url)
    - anonymous() : 익명사용자의 접근 허용
    - hasRole(String) : 롤에 따라 접근 허용
    - hasAuthority(String) : 권한에 따라 접근 허용 (ROLE_ : prefix가 붙는 것)
    - hasAnyRole(String) : 사용자가 주어진 권한 중 어떤것이라도 있다면 접근 허용
    - hasIpAddress(String) : 주어진 IP로부터 요청이 왔을 경우 접근 허용
    - permitAll() : 무조건 접근 허용
    - authenticated() : 인증된 사용자의 접근을 허용
    - fullyAuthenticated() : 인증된 사용자의 접근을 허용, rememberMe 인증 제외
    - access(hasRole(ROLE)) : 주어진 SpEL 표현식의 평가 결과에 따라 접근 허용, 좀 더 자세하게 작성 가능
    - denyAll() : 무조건 접근 불가

## Authentication Structure
- principal : 사용자 아이디 혹은 User 객체를 저장
- credentials : 사용자 비밀번호
- authorities : 인증된 사용자의 권한 목록
- details :  인증 부가 정보
- Authenticated : 인증 여부

## Authentication Flow
1. username + password (Authentication)
2. AuthenticationManager → AuthenticationProvider (인증 진행, 실패시 AuthenticationException)
3. User + Authorities (Authentication)
4. SecurityContext(현재 인증한 User정보가 있는 객체)를 SecurityContextHolder에 저장
5. SuccessHandler

## Logout
- 세션 무효화
- 인증토큰 삭제
- 쿠키정보 삭제
- 로그인 페이지로 리다이렉트
```
http.logout()
    .logoutUrl("/logout")
    .logoutSuccessUrl("/login")
    .deleteCookies("JSESSIONID", "remeber-me")
    .addLogoutHandler(logoutHandler())
    .logoutSuccessHandler(logoutSuccessHandler())
```

## SessionManagementFilter
- 세션 관리: 로그인 인증시 유저의 세션정보를 등록, 조회, 삭제 등의 이력 관리
- 동시 세션 제어 : 동시 로그인 허용 여부 밑 동일 아이디로 최대 접속 가능한 세션 수 제한
- 세션 고정 보호 : 인증 할 때마다 세션쿠키를 새로 발급하여 공격자의 쿠키 조작을 통한 공격을 방지
- 세션 생성 정책 
  - Always
  - If_Required
  - Never
  - Stateless
  
## Form Authentication - CSRF(사이트 간 요청 위조)
1. 사용자가 로그인 후 인증 정보 받음
2. 공격자가 이메일등으로 사용자에게 링크를 보냄
3. 사용자가 링크를 클릭하면 공격용 웹페이지로 이동 (공격자의 의도가 심어진 명령이 하달?) ...
- csrfFilter
  - client : 타임리프는 form태그 사용시 자동으로 csrf 토큰을 생성해주나 jsp는 `<input type="hidden" name="${csrf.parameterName}" value="${csrf.token}" />` 와 같이 태그를 추가해 줘야 한다.  
  - Spring Security : http.csrf (default)
  
## SecurityContextHolder & SecurityContext
- SecurityContext
  - Authentication객체의 저장소
  - ThreadLocal에 저장되며, 아무곳에서나 참조가 가능하다
  - 인증 완료 후 HttpSession에 저장하여 어플리케이션 전역에서 참조 가능하다.

- SecurityContextHolder
  - SecurityContext 객체 저장 방식
    - MODE_THREADLOCAL : 쓰레드당 SecurityContext 객체를 할당 (default)
    - MODE_INHERITABLETHREADLOCAL : 메인 스레드와 자식 스레드가 동일한 SecurityContext를 유지
    - MODE_GLOBAL : 어플리케이션에서 단 하나의 SecurityContext를 저장한다.
  - SecurityContextHolder.clearContext() : SecurityContext 초기화
  
어디서나 호출 가능한 객체
```
Authentication authentication = SecurityContextHolder.getContext().getAuthentication()
```