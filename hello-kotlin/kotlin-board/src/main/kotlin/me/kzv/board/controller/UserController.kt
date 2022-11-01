package me.kzv.board.controller

import me.kzv.board.service.UserService
import me.kzv.board.controller.dto.SignInRequestDto
import me.kzv.board.controller.dto.SignUpRequestDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(val userService: UserService) {

    @PostMapping("/sign-up")
    fun signUp(@RequestBody dto: SignUpRequestDto): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.CREATED).body("회원가입 성공 (id=${userService.signUp(dto)})")
    }

    @PostMapping("/sign-in")
    fun signIn(@RequestBody dto: SignInRequestDto): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.OK).body("로그인 성공 (id=${userService.signIn(dto)})")
    }
}