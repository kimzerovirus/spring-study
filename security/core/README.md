# 스프링 시큐리티 실전 - 로그인 인증/인가 구현하기

스프링 시큐리티의 기본 동작 방식은 여러개의 Filter 객체들이 순차적으로 수행 되는 방식이다. 특히 인증이 이루어지는 Filter 객체는 `UsernamnePasswordAuthenticationFilter` 이다. 이 객체는 `AbstractAuthenticationFilter` 를 상속받고 있다. 그리고 이 객체 안에는 `attemptAuthentication()` 이라는 추상 메서드가 존재하는데, 이 메서드는 `getAuthenticationManager` 에 인증된 토큰을 담아서 반환한다. 토큰 객체는 `AbstractAuthenticationToken` 객체를 상속 받아 구현 할 수 있다. (Default는 `UsernamePasswordAuthenticationToken` 이다.) 이 토큰은 생성될 때는 인증 전 사용자가 입력한 값을 받지만 인증 이후에는 인증된 결과 값을 담아 `SecurityContextHolder` 에 등록될 `Authentication` 객체이다.



`AuthenticationManager` 는 실제 인증 로직이 있는 객체인 `AuthenticationProvider` 객체를 관리하는 객체이다. 따라서 `AuthenticationProvider` 인증이 완료 되면 `AuthenticationManager` 는 인증된 `Authentication` 객체를 반환하는 기능을 수행한다.

## PasswordEncoder

### 생성

- `PasswordEncoderFactories.createDelegatingPasswordEncoder()`
- 여러개의 PasswordEncoder 유형을 선언 후, 상황에 맞게 선택하여 사용하는 Encoder
- `new BCryptPasswordEncoder()` 를 반환하는 메소드를 스프링 빈으로 등록하여 특정 Encoder를 바로 사용할 수도 있다.

### 인터페이스

- `encode(password)` : 비밀번호 암호화
- `matches(rawPwd, encodedPwd)` : 비밀번호 비교

## UserDetailsService

스프링 시큐리티에서 유저의 정보를 가져오는 인터페이스이다.

### 인터페이스

- `loadUserByUsername` : UserDetails 객체를 반환해주는 메소드로 이 메소드 안에서 username의 유무에 따른 처리를 해주면 되는듯?

## AuthenticationProvider

UserDetailsService에서 반환한 유저 객체를 추가 검증 할 때 사용하는 구현체이다.

- `authenticate` : 인증 처리에 관한 메서드로 서비스 정책에 따른 로그인 검증 로직을 구현하면 된다 - 여기서 비밀번호 검증 등을 해주면 되는듯?
- `supports` : 매개변수로 받은 `Authentication` 객체의 구현 클래스가 `AuthenticationProvider` 객체에서 사용하는 `Authentication` 객체와 같은지 확인하는 메서드이다. 따라서 boolean을 반환하며, true 이면 authenticatae 메서드로 넘어가는 듯?

https://docs.spring.io/spring-security/site/docs/4.0.x/apidocs/org/springframework/security/authentication/AuthenticationProvider.html

## Authentication

### `Authentication` 객체의 정보

- `principal` : 사용자 아이디 또는 `User` 객체를 저장한다.
- `credentials` : 사용자 비밀번호
- `authorities` : 인증된 사용자의 권한 목록
- `details` : 인증 부가 정보



## 인증 흐름

1. UsernamePasswordAuthenticationFilter : 인증 전 토큰 객체(id, pwd)를 생성 (구현체는 AbstractAuthenticationProcessingFilter)
2. AuthenticationManager - 인증 관리자 : 토큰 객체 생성등 인증의 전반적인 프로세스 관리를 하며, 실제 인증 처리는 AuthenticationProvider 에 위임
3. AuthenticationProvider - 인증 처리자 : 실제 인증 처리 (userDetailsService에서 준 유저 정보를 바탕으로 패스워드 검증)
4. userDetailsService : 유저 객체 조회 UserDetails 반환 (아이디 검증)

## `Authentication`  객체가 `SecurityContext` 에 저장되는 흐름

1. `UsernamePasswordAuthenticationFilter` 에서 `Authentication` 객체 생성 
2. 인증 전 `Authentication`의 principal은 username, credentials는 password(`UsernamePasswordAuthenticationToken` 은 `Authentication` 구현체) 
3. `Authentication` 객체 `AuthenticationManager` 로 전달 
4. 인증 후 `Authentication` 를 갱신한다. 이 때 `Authentication` 에 principal은 UserDetails , credentials는 null로 설정하고 GrantedAuthority 권한 목록을 담아준다.
5. 인증정보를 `SecurityContextHolder` (ThreadLocal)에 저장
