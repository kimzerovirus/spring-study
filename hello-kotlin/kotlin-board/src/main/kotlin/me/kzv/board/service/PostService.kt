package me.kzv.board.service

import me.kzv.board.controller.dto.PostWriteRequestDto
import me.kzv.board.controller.dto.PostsDetailCommentsResponseDto
import me.kzv.board.entity.Comment
import me.kzv.board.repository.CommentRepository
import me.kzv.board.repository.LikeRepository
import me.kzv.board.entity.enums.LikeType
import me.kzv.board.controller.dto.PostsResponseDto
import me.kzv.board.entity.Post
import me.kzv.board.repository.PostRepository
import me.kzv.board.controller.dto.PostsDetailResponseDto
import me.kzv.board.repository.UserRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import java.util.stream.Collectors
import javax.transaction.Transactional

@Service
class PostService(val postRepository: PostRepository, val userRepository: UserRepository,
                  val commentRepository: CommentRepository, val likeRepository: LikeRepository
) {

    fun getAllPosts(): MutableList<PostsResponseDto>? {
        return postRepository.findAll()
                .stream()
                .map { post: Post -> PostsResponseDto(post.id!!, post.title, post.content, post.writer, post.likeCnt, post.dislikeCnt) }
                .collect(Collectors.toList())
    }

    fun get10PostsDetail(userId: Long, page: Int): MutableList<PostsDetailResponseDto>? {
        val pageable: Pageable = PageRequest.of(page, 10)
        return postRepository.findAll(pageable)
                .stream()
                .map { post: Post -> PostsDetailResponseDto(post.id!!, post.title, post.content, post.writer, post.likeCnt, post.dislikeCnt,
                        checkMyLike(userId, post.id!!), getComments(post.id!!))
                }
                .collect(Collectors.toList())
    }

    fun checkMyLike(userId: Long, postId: Long): LikeType? {
        if(likeRepository.findByUserIdAndPostId(userId, postId).isPresent) {
            return likeRepository.findByUserIdAndPostId(userId, postId)
                    .orElseThrow { RuntimeException() }
                    .likeType
        }
        return null
    }

    fun getComments(postId: Long): MutableList<PostsDetailCommentsResponseDto> {
        val pageable: Pageable = PageRequest.of(0, 5)
        return commentRepository.findAllByPostId(postId, pageable)
                .stream()
                .map { comment: Comment -> PostsDetailCommentsResponseDto(comment.writer, comment.content) }
                .collect(Collectors.toList())
    }

    fun getMy10Posts(userId: Long, page: Int): MutableList<PostsResponseDto>? {
        val pageable: Pageable = PageRequest.of(page, 10)   // Sort.Direction.DESC, "createTime" 추가하면 최신순
        return postRepository.findAllByUserId(userId, pageable)
                .stream()
                .map { post: Post -> PostsResponseDto(post.id!!, post.title, post.content, post.writer, post.likeCnt, post.dislikeCnt) }
                .collect(Collectors.toList())
    }

    fun writePost(dto: PostWriteRequestDto): Long {
        val user = userRepository.findById(dto.userId)
                .orElseThrow { RuntimeException() }
        val post = Post(title = dto.title, content = dto.content, writer = user.name, userId = user.id!!)
        return postRepository.save(post).id!!
    }

    @Transactional
    fun deletePost(postId: Long) {
        postRepository.deleteById(postId)
        deleteRelatedComments(postId)
        deleteRelatedLikes(postId)
    }

    fun deleteRelatedComments(postId: Long) {
        val comments = commentRepository.findAllByPostId(postId)
        for(comment in comments) {
            commentRepository.delete(comment)
        }
    }

    fun deleteRelatedLikes(postId: Long) {
        val likes = likeRepository.findAllByPostId(postId)
        for(like in likes) {
            likeRepository.delete(like)
        }
    }
}