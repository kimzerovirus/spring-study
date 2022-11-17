package me.kzv.mvc.utils;

import me.kzv.mvc.web.controller.Controller;
import me.kzv.mvc.web.controller.HomeController;
import me.kzv.mvc.web.controller.UserListController;

import java.util.HashMap;
import java.util.Map;

public class MyRequestMappingHandlerMapping {
    private Map<String, Controller> mappings = new HashMap<>();

    void init(){
        mappings.put("/", (Controller) new HomeController());
        mappings.put("/users", new UserListController());
    }

    public Controller findHandler(String urlPath) {
        return mappings.get(urlPath);
    }
}
