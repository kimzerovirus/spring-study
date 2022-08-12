package com.example.uploadsample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UploadClientController {
    @GetMapping("/uploadEx")
    public String uploadEx(){
        return "uploadEx";
    }
}
