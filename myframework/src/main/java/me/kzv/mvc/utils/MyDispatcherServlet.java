package me.kzv.mvc.utils;

import me.kzv.mvc.WebApplicationServer;
import me.kzv.mvc.web.controller.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/") // 어떤 경로로 입력하더라도 이 서블릿이 호출된다.
public class MyDispatcherServlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(WebApplicationServer.class);
    private MyRequestMappingHandlerMapping requestMappingHandlerMapping;

    @Override
    public void init() throws ServletException {
        requestMappingHandlerMapping = new MyRequestMappingHandlerMapping();
        requestMappingHandlerMapping.init();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("DispatcherServlet#service");
        try {
            Controller handler = requestMappingHandlerMapping.findHandler(request.getRequestURI()); // 해당 컨트롤러 찾기

            String viewName = handler.handlerRequest(request, response);
            log.info("view name : {}", viewName);

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/" + viewName); // 대소문자 구별함... home.jsp != Home.jsp
            requestDispatcher.forward(request, response);

        } catch (Exception e) {
            log.error("exception occurred: [{}]", e.getMessage(), e);
            throw new ServletException(e);
        }
    }
}
