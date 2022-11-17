package me.kzv.mvc.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {
    String handlerRequest(HttpServletRequest request, HttpServletResponse response) throws Exception;

    String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
