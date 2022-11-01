package me.kzv.board.controller

import me.kzv.board.controller.dto.PostWriteRequestDto
import me.kzv.board.service.PostService
import me.kzv.board.controller.dto.PostsDetailResponseDto
import me.kzv.board.controller.dto.PostsResponseDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class PostController(val postService: PostService) {

    @GetMapping("/posts")
    fun getAllPosts(): ResponseEntity<MutableList<PostsResponseDto>> {
        return ResponseEntity.status(HttpStatus.OK).body(postService.getAllPosts())
    }

    @GetMapping("/users/{userId}/posts/detail/paging")
    fun get10PostsDetail(@PathVariable userId: Long,
                         @RequestParam page: Int): ResponseEntity<MutableList<PostsDetailResponseDto>> {
        return ResponseEntity.status(HttpStatus.OK).body(postService.get10PostsDetail(userId, page))
    }

    @GetMapping("/users/{userId}/my-posts/paging")
    fun getMy10Posts(@PathVariable userId: Long,
                     @RequestParam page: Int): ResponseEntity<MutableList<PostsResponseDto>> {
        return ResponseEntity.status(HttpStatus.OK).body(postService.getMy10Posts(userId, page))
    }

    @PostMapping("/posts")
    fun writePost( @RequestBody dto: PostWriteRequestDto): ResponseEntity<String> {
        val postId = postService.writePost(dto)
        return ResponseEntity.status(HttpStatus.CREATED).body("게시글 작성 성공 (id=$postId)")
    }

    @DeleteMapping("/posts/{postId}")
    fun deletePost(@PathVariable postId: Long): ResponseEntity<String> {
        postService.deletePost(postId)
        return ResponseEntity.status(HttpStatus.OK).body("게시글 삭제 완료")
    }
}
