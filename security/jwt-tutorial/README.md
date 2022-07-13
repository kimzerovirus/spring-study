# JWT Tutorial

> https://bcp0109.tistory.com/301 해당 포스트를 보고 정리하였습니다.

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