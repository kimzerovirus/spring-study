# SpringMVC JDBC

 > 인텔리제이에서 설정하는 법: maven프로젝트 생성 → projectstructure → 모듈에 스프링과 web(연필아이콘 클릭하여 WEB-INF폴더 경로 설정)추가
---

### 1. Dispatcher Servlet을 FrontController로 설정하기

 - ```web.xml```파일에 설정
 - ```org.springframework.web.WebApplicationInitializer```인터페이스를 구현해서 사용
 - ```javax.servlet.ServletContainerInitializer```사용 (서블릿 3.0스펙 이상에서 web.xml 대신 사용할 수 있다.)

### 2. @Configuration과 @Bean
 - Java Config파일임을 등록하고 스프링 컨테이너에 새로운 빈 객체를 제공할 수 있다.

### 3. @EnableWebMvc
 - DispatcherServlet의 RequestMappingHandlerMapping, RequestMappingHandlerAdapter, ExceptionHandlerExceptionResolver, MessageConverter 등 Web에 필요한 빈들을 대부분 자동으로 설정해준다.
 - xml 설정의 ```<mvc:annotation-driven/>``` 와 동일하다.
 - 기본 설정 이외의 설정이 필요하다면 WebMvcConfigurerAdapter 를 상속받도록 Java config class를 작성한 후, 필요한 메소드를 오버라이딩 하도록 한다.

### 4.






---
출처: 네이버 부스트코스 웹프로그래밍(풀스택)