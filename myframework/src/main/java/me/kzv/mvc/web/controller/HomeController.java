package me.kzv.mvc.web.controller;


import me.kzv.mvc.annotation.MyController;
import me.kzv.mvc.annotation.MyRequestMapping;
import me.kzv.mvc.annotation.MyRequestMethod;
import me.kzv.mvc.web.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@MyController
public class HomeController {
    @MyRequestMapping(value = "/", method = MyRequestMethod.GET)
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("users", UserRepository.findAll());
        return "home";
    }
}
