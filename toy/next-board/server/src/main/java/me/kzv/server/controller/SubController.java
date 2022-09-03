package me.kzv.server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/subs")
@RestController
@RequiredArgsConstructor
public class SubController {

    @GetMapping("/{name}")
    public void getSub(@PathVariable String name) {

    }

    @PostMapping("/")
    public void createSub() {

    }

    @GetMapping("/sub/topSubs")
    public void topSubs(){

    }

    @PostMapping("/{name}/upload")
    public void uploadSubImage(@PathVariable String name){

    }

}
