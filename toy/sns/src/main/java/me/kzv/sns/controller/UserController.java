package me.kzv.sns.controller;

import lombok.RequiredArgsConstructor;
import me.kzv.sns.controller.request.UserJoinRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public void join(@RequestBody UserJoinRequest request){
        userService.join(request.getUserName(), request.getPassword());
    }

}
