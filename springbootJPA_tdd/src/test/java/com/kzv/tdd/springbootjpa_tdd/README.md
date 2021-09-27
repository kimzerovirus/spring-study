# 테스트

---
### 1. WebMvcTest
 - 선언시 @Controller, @ControllerAdvice등을 사용할 수 있다.
 - @Service, @Component, @Repository 등은 사용 불가, 컨트롤러만 사용하기 때문에 선언한다.

### 2. private MockMvc mvc
 - 웹 API HTTP, GET,POST등에 대한 테스트시 사용
 - 스프링 MVC테스트의 시작점이다.

### 3. andException(status.isOk())
 - mvc.perform의 결과 검증
 - HTTP Header의 Status 검증 200, 404, 500 등
 - OK(200)을 검증한다.

### 4. andException(content().string(hello))
 - Controller에서 "hello"를 리턴하는게 맞는 지 값을 검증한다.