package com.example.springmvc1.controller;

import com.example.springmvc1.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EventController {

    @Autowired
    EventService eventService;

    //@RequestMapping(value = "/events", method = RequestMethod.GET)
    //spring4.3버전부터는 아래와 같이 @GetMapping으로 변경되었다.
    @GetMapping("/events")
    public String events(Model model){
        // key : value
        model.addAttribute("events", eventService.getEvents());

        return "events"; //view파일 이름
    }
}
