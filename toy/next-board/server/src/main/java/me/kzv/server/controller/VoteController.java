package me.kzv.server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/votes")
@RestController
@RequiredArgsConstructor
public class VoteController {

    @PostMapping("/")
    public void vote(){

    }
}
