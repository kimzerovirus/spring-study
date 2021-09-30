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

### 5. @After
 - Junit에서 단위 테스트가 끝날 때마다 수행되는 메소드를 지정한다.
 - 여러번 테스트를 시도해서 테스트 찌거기 데이터가 db에 남아 오류가 나는것을 방지한다.

### 6. 엔티티명(테이블명)레포지토리.save 
 - 해당 테이블에 대해 insert/update 쿼리를 실행한다.
 - id가 없을시 insert, 있다면 update 쿼리룰 실행한다.

### 7. 엔티티명(테이블명)레포지토리.findall
 - 해당 테이블의 모든 데이터를 조회한다.