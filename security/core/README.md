# 스프링 시큐리티 실전

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
- `supports` : 정상적인 인증 처리과정인지 검증하는 메서드인듯? boolean을 반환하여 true 이면 authenticatae 메서드로 넘어가는 듯?

https://docs.spring.io/spring-security/site/docs/4.0.x/apidocs/org/springframework/security/authentication/AuthenticationProvider.html

