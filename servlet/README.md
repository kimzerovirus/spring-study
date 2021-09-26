# Servlet

---
### 1. 서블릿이란...?
 - 서블릿은 서버 쪽에서 실행되면서 클라이언트의 요청에 따라 동적으로 서비스를 제공하는 자바 클래스이다.
 - 서블릿은 일반적인 자바프로그래밍과 달리 혼자 실행할 수 없고 톰캣과 같은 JSP/Servlet컨테이너에서 실행된다.

### 2. 서블릿 API
#### 2.1 Servlet 인터페이스
 - javax.servlet패키지로 Servlet관련 추상 메서드를 선언한다.
 - init(), service(), destroy(), getServletinfo(), getServletConfig()를 선언한다.
#### 2.2 ServletConfig 인터페이스
 - 서블릿의 기능 관련 추상 메서드들이 들어있다.
 - getInitParameter(), getInitParametersNames(), getServletContext(), getServletName()
#### 2.3 GenericServlet 클래스
 - 위의 두 인터페이스를 구현하여 일반적인 서블릿 기능을 구현한 클래스로 여러 통신 프로토콜에 대한 클라이언트/서버 프로그램에서 사용한다.
 - GenericServlet을 상속받아 구현하면 사용되는 프로토콜에 따라 각각의 service()를 오버라이딩해서 구현해야한다.
#### 2.4 HttpServlet 클래스
 - GenericServlet을 상속받아 HTTP 프로토콜을 사용하는 서블릿 기능을 수행한다.
 - 요청 시 service()가 호출되면서 요청방식에 따라 doGet()또는 doPost()가 호출된다.
>  - protected doDelete(HttpServletRequest req, HttpServletResponse res)
>  - protected doGet(HttpServletRequest req, HttpServletResponse res)
>  - protected doHead(HttpServletRequest req, HttpServletResponse res)
>  - protected doPost(HttpServletRequest req, HttpServletResponse res)
>  - [HTTP 요청 메서드란?](https://developer.mozilla.org/ko/docs/Web/HTTP/Methods)

### 3. 서블릿 생성주기
 - 초기화 : init()
 - 작업 : doGet(), doPost()
 - 종료 : destroy()

### 4. 사용자 정의 서블릿 생성하기
 - HttpServlet 클래스를 상속받아 생성주기 메서드를 오버라이딩하여 구현한다.
```
    public class HelloServlet extends HttpServlet{
        
        @Override
        public void init(){
            ...
        }
        
        @Override
        public void doGet(HttpServletRequest req, HttpServletResponse res){
            ...
        }
        
        @Override
        public void destroy(){
            ...
        }
    }
```

