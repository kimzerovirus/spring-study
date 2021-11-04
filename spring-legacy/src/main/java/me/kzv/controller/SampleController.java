package me.kzv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SampleController {

    @GetMapping("/sample")
    public String sample(){
        System.out.println("hello");

        return "sample";
    }

}
