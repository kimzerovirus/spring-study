package me.kzv.mvc.utils;

import me.kzv.mvc.view.MyModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface MyHandlerAdapter {
    boolean supports(Object handler);

    MyModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception;
}
