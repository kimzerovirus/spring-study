package me.kzv.di.controller;


import me.kzv.di.annotation.Controller;
import me.kzv.di.annotation.Inject;
import me.kzv.di.service.UserService;

@Controller
public class UserController {

    private final UserService userService;

    @Inject
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public UserService getUserService() {
        return userService;
    }
}
