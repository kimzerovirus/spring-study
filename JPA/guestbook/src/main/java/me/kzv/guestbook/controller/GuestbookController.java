package me.kzv.guestbook.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@RequestMapping("/guestbook")
@Controller
public class GuestbookController {

    @GetMapping({"/","/list"})
    public String list(){
        return "/guestbook/list";
    }
}
