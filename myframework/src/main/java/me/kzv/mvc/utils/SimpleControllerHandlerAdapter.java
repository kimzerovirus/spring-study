package me.kzv.mvc.utils;

import me.kzv.mvc.view.MyModelAndView;
import me.kzv.mvc.web.controller.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SimpleControllerHandlerAdapter implements MyHandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        return false;
    }

    @Override
    public MyModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String viewName = ((Controller) handler).handleRequest(request, response);
        return new MyModelAndView(viewName);
    }
}
