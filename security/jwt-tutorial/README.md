# JWT Tutorial

> https://bcp0109.tistory.com/301 해당 포스트를 보고 정리하였습니다.

스프링 시큐리티는 스프링 시큐리티 세션을 들고 있다.
그러면 원래 서버 세션 영역 안에 시큐리티가 관리하는 세션이 따로 존재하게 된다.

시큐리티 세션에는 무조건 Authentication 객체 만 들어갈 수 있다.
Authentication가 시큐리티세션 안에 들어가 있다는 것은 로그인된 상태라는 의미이다.
Authentication에는 2개의 타입이 들어갈 수 있는데 UserDetails, OAuth2User이다.

문제점 :
이때 세션이 2개의 타입으로 나눠졌기 때문에 컨트롤러에서 처리하기 복잡해진다는 문제점이 발생한다!
왜냐하면 일반적인 로그인을 할 때엔 UserDetails 타입으로 Authentication 객체가 만들어지고,
구글 로그인처럼 OAuth 로그인을 할 때엔 OAuth2User 타입으로 Authentication 객체가 만들어지기 때문이다.

해결방법 :
PrincipalDetails에 UserDetails, OAuth2User를 implements한다.
우리는 오직 PrincipalDetails 만 활용하면 된다.

### 회원가입
```
POST http://localhost:8080/auth/signup

{
  "email": "test@test.net",
  "password": "1234"
}
```

### 로그인
```
POST http://localhost:8080/auth/login

{
  "email": "test@test.net",
  "password": "1234"
}
```
로그인 검증 로직은  `CustomDetailsService`클래스에서 이루어지는데 실제 비밀번호 검증은 이를 호출하는 `DaoAuthenticationProvider`에서 이루어진다.
```
    DaoAuthenticationProvider.class

	@Override
	@SuppressWarnings("deprecation")
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		if (authentication.getCredentials() == null) {
			this.logger.debug("Failed to authenticate since no credentials provided");
			throw new BadCredentialsException(this.messages
					.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
		}
		String presentedPassword = authentication.getCredentials().toString();
		if (!this.passwordEncoder.matches(presentedPassword, userDetails.getPassword())) {
			this.logger.debug("Failed to authenticate since password does not match stored value");
			throw new BadCredentialsException(this.messages
					.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
		}
	}
```


### 재발급
```
POST http://localhost:8080/auth/reissue

{
  "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIyIiwiYXV0aCI6IlJPTEVfVVNFUiIsImV4cCI6MTYxNTExNDI4MH0.43LvabP41Awhicy6YYAYHtDPnxNYpEygtE-DjLaDjNpAxZf01Nx4xE_dGk0V4jBpjwCgKVGKZIMyEeIppwzARQ",
  "refreshToken": "eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE2MTU3MTcyODB9.DKqk-EZVT0TJAvvHpSN8nClIHKq-k4KYMHpx-Ltf7V8OB6Og4D_dsYnr3Z4Rw7iR7ckv-ZWMyi5SkheESw-T0g"
}
```