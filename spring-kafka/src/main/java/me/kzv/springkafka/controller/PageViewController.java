package me.kzv.springkafka.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class PageViewController {
    @GetMapping("/")
    public String home(Model model){
        log.info("--home page--");
        return "home";
    }
}
