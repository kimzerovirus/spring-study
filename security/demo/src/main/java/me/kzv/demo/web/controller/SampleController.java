package me.kzv.demo.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/sample")
@Controller
public class SampleController {

    @GetMapping("/member")
    public String exMember(){
        return "sample/member";
    }
}
