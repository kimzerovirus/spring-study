package me.kzv.mvc.utils;

import me.kzv.mvc.view.MyModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyAnnotationHandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return handler instanceof MyAnnotationHandler;
    }

    @Override
    public MyModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String viewName = ((MyAnnotationHandler) handler).handle(request, response);
        return new MyModelAndView(viewName);
    }
}