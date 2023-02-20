package me.kzv.hellospringboot;

import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MySpringApplication {
    public static void run(Class<?> application, String... args) {
        //        GenericApplicationContext applicationContext = new GenericApplicationContext(); // front controller
//        GenericWebApplicationContext applicationContext = new GenericWebApplicationContext(); // 자바로 만든 configuration 정보를 읽을 수 없음
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() { // config 읽어올 수 있음
            @Override
            protected void onRefresh() {
                super.onRefresh();

                // 서블릿 작업을 스프링 컨테이너가 초기화 되는 순간에 같이 일어나도록 옮김 - 스프링부트가 이런식으로 설계되어 있음
                ServletWebServerFactory serverFactory = this.getBean(ServletWebServerFactory.class); // Jetty Netty 등을 집어넣으면 서버 교체 완료 // this = applicationContext
                DispatcherServlet dispatcherServlet = this.getBean(DispatcherServlet.class);
                dispatcherServlet.setApplicationContext(this); // 스프링 컨테이너가 알아서 주입해줘서 applicationContext 를 세팅 해주지 않아도 동작한다

                WebServer webServer = serverFactory.getWebServer(servletContext -> {
//            HelloController helloController = new HelloController(); // 스프링 컨테이너로 만듦

                    /** front controller */
//            servletContext.addServlet("frontcontroller", new HttpServlet() {
//                @Override
//                protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//                    // 웹 3가지 요소 상태코드, 헤더, 바디
//                    // 프론트 컨트롤러 - 인증, 보안, 다국어, 공통 기능
//
//                    if (req.getRequestURI().equals("/hello") && req.getMethod().equals(GET.name())) {
//                        String name = req.getParameter("name");
//
//                        HelloController helloController = applicationContext.getBean(HelloController.class);
//                        String result = helloController.hello(name);
//
//                        resp.setStatus(OK.value());
////                        resp.setHeader(CONTENT_TYPE, TEXT_PLAIN_VALUE);
//                        resp.setContentType(TEXT_PLAIN_VALUE);
//                        resp.getWriter().println(result);
//                    } else if (req.getRequestURI().equals("/user")) {
//                        //
//                    } else{
//                        resp.setStatus(NOT_FOUND.value());
//                    }
//                }
//            }).addMapping("/*");

                    /** dispatcher servlet */
//                    servletContext.addServlet("dispatcherServlet",
//                            new DispatcherServlet(this)
//                    ).addMapping("/*");
                    servletContext.addServlet("dispatcherServlet", dispatcherServlet).addMapping("/*"); // bean 으로 등록
                });
                webServer.start();
            }
        }; // dispatcher servlet
//        applicationContext.registerBean(HelloController.class);
//        applicationContext.registerBean(SimpleHelloService.class);
        applicationContext.register(application);
        applicationContext.refresh();

//        SpringApplication.run(HelloSpringbootApplication.class, args);

//        new Tomcat().start(); TomcatServletWebServerFactory 가 알아서 설정함
    }

}
