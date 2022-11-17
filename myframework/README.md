## 프론트 컨트롤러 패턴

- 모든 요청을 단일 handler(처리기)에서 처리하도록 하는 패턴
- 스프링 웹 MVC 프레임워크의 DispatcherServlet이 프론트 컨트롤러 패터으로 구현 되어 있다. 즉, 모든 요청을 DispatcherServlet이 받아 적절한 컨트롤러에게 위임하는 역할을 한다.

## Forward

- 서블릿에서 클라이언트(웹 브라우저)를 거치지 않고 바로 다른 서블릿에게 요청하는 방식

- Forward 방식은 서버 내부에서 일어나는 요청이기 때문에 HttpServletRequest, HttpServletResponse객체가 새롭게 생성되지 않는다.(공유됨)

  ```
  RequestDispatcher dispatcher = request.getRequestDispatcher("포워드 할 서블릿 또는 JSP")
  dispatcher.forward(requset, response)
  ```

  

## Redirect

- 서블릿이 클라이언트를 다시 거쳐 다른 서블릿에게 요청하는 방식
- Redirect 방식은 클라이언트로부터 새로운 요청이기 때문에 새로운 HttpServletRequest, HttpServletResponse객체가 생성 된다.
- sendRedirect()

## mvc

![asdf](https://k.kakaocdn.net/dn/cAkzFN/btqBp4AIlD3/mE8PbHZQh0WtvB0wqULb3k/img.png)