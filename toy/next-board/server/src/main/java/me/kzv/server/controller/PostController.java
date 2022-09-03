package me.kzv.server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/posts")
@RestController
@RequiredArgsConstructor
public class PostController {

    @GetMapping({"/", "/{identifier}/{slug}"})
    public void getPosts(@PathVariable String identifier, @PathVariable String slug) {

    }

    @PostMapping("/create")
    public void createPost() {

    }

    @GetMapping("/{identifier}/{slug}/comments")
    public void getPostComment(@PathVariable String identifier, @PathVariable String slug) {

    }

    @PostMapping("/{identifier}/{slug}/comments")
    public void createPostComment(@PathVariable String identifier, @PathVariable String slug) {

    }
}
