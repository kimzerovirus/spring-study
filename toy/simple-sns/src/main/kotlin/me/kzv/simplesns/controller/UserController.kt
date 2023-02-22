package me.kzv.simplesns.controller

import me.kzv.simplesns.controller.request.UserJoinRequest
import me.kzv.simplesns.controller.response.Response
import me.kzv.simplesns.controller.response.UserJoinResponse
import me.kzv.simplesns.service.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/users")
class UserController(
    private val userService: UserService,
) {
    @PostMapping("/join")
    fun join(@RequestBody request: UserJoinRequest): Response<UserJoinResponse> {
        val user = userService.join(userName = request.userName, password = request.password)
        return Response.success(UserJoinResponse.fromUser(user))
    }
}