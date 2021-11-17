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

### 4. @ComponentScan
 - ```ComponentScan```애노테이션을 이용하면 ```Controller```, ```Service```, ```Repository```, ```Component```애노테이션이 붙은 클래스를 찾아 스프링 컨테이너가 관리하게 된다.
 - ```DefaultAnnotationHandlerMapping```과 ```RequestMappingHandlerMapping```구현체는 다른 핸드러 매핑보다 훨씬 더 정교한 작업을 수행한다. 이 두 개의 구현체는 애노테이션을 사용해 매핑 관계를 찾는 매우 강력한 기능을 가지고 있다. 이들 구현체는 스프링 컨테이너 즉 애플리케이션 컨텍스트에 있는 요청 처리 빈에서 ```RequestMapping```애노테이션을 클래스나 메소드에서 찾아 ```HandlerMapping```객체를 생성하게 된다.
 - ```HandlerMapping```은 서버로 들어온 요청을 어느 핸들러로 전달할지 결정하는 역할을 수행한다.
 - ```DefaultAnnotationHandlerMapping```은 ```DispatcherServlet```이 기본으로 등록하는 기본 핸들러 맵핑 객체이고, ```RequestMappingHandlerMapping```은 더 강력하고 유연하지만 사용하려면 명시적으로 설정해야 한다.

### 5. WebMvcConfigurerAdapter
 - org.springframework.web.servlet.config.annotation. WebMvcConfigurerAdapter
 - ```@EnableWebMvc``` 를 이용하면 기본적인 설정이 모두 자동으로 되지만, 기본 설정 이외의 설정이 필요할 경우 해당 클래스를 상속 받은 후, 메소드를 오버라이딩 하여 구현한다.

### 6. @Controller, @RestController와 @RequestMapping
 - ```@Controller```는 view를 반환하는데 사용한다.
 - ```@RestController```는 SpringMVC Controller에 ```@ResponseBody```가 추가된 것으로 Json 형태로 객체 데이터를 반환한다.
 - Http 요청과 이를 다루기 위한 Controller 의 메소드를 연결하는 어노테이션
> #### Http Method 와 연결하는 방법
> - @RequestMapping(value="/users", method=RequestMethod.POST)
> - From Spring 4.3 version (@GetMapping, @PostMapping, @PutMapping, @DeleteMapping, @PatchMapping)
> #### Http 특정 해더와 연결하는 방법
> - @RequestMapping(method = RequestMethod.GET, headers = "content-type=application/json")
> #### Http Parameter 와 연결하는 방법
> - @RequestMapping(method = RequestMethod.GET, params = "type=raw")
> #### Content-Type Header 와 연결하는 방법
> - @RequestMapping(method = RequestMethod.GET, consumes = "application/json")
> #### Accept Header 와 연결하는 방법
> - @RequestMapping(method = RequestMethod.GET, produces = "application/json")
> 


---
출처: 네이버 부스트코스 웹프로그래밍(풀스택)