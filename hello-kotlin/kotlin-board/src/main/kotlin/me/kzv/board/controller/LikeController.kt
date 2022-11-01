package me.kzv.board.controller

import me.kzv.board.service.LikeService
import me.kzv.board.controller.dto.LikeRequestDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class LikeController(val likeService: LikeService) {

    @PostMapping("/likes")
    fun requestLike(@RequestBody likeRequestDto: LikeRequestDto): ResponseEntity<String> {
        val likeType = likeService.requestLike(likeRequestDto)
        return ResponseEntity.status(HttpStatus.CREATED).body("$likeType 요청 성공")
    }

    @DeleteMapping("/posts/{postId}/likes/users/{userId}")
    fun cancelLike(@PathVariable userId: Long, @PathVariable postId: Long): ResponseEntity<String> {
        val likeType = likeService.cancelLike(userId, postId)
        return ResponseEntity.status(HttpStatus.CREATED).body("$likeType 취소 성공")
    }
}