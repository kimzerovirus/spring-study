package me.kzv.springjpa_webservice.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Log4j2
@RequestMapping("/sample")
@Controller
public class SampleController {

    //test
    @GetMapping
    public void ex(){
        log.info("ex............");
    }
}
