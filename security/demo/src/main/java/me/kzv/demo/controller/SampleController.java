package me.kzv.demo.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequestMapping("/sample")
public class SampleController {
    @GetMapping("/all")
    public void exAll() {
        log.info("exAll.........");
    }

    @GetMapping("/member")
    public void exMember(){
        log.info("exMember..........");
    }

    @GetMapping("/admin")
    public void exAdmin(){
        log.info("exAdmin............");
    }
}
