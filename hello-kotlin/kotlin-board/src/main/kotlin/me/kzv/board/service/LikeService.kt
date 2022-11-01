package me.kzv.board.service

import me.kzv.board.controller.dto.LikeRequestDto
import me.kzv.board.entity.enums.LikeType
import me.kzv.board.entity.Like
import me.kzv.board.repository.LikeRepository
import me.kzv.board.repository.PostRepository
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import javax.transaction.Transactional

@Service
class LikeService(val likeRepository: LikeRepository, val postRepository: PostRepository) {

    @Transactional
    fun requestLike(dto: LikeRequestDto): LikeType {
        val like = likeRepository.save(Like(likeType = dto.likeType, userId = dto.userId, postId = dto.postId))
        val post = postRepository.findById(dto.postId)
                .orElseThrow { RuntimeException() }

        if (like.isLike()) {
            post.increaseLikeCnt()
        } else {
            post.increaseDislikeCnt()
        }

        return like.likeType
    }

    @Transactional
    fun cancelLike(userId: Long, postId: Long): LikeType {
        val like = likeRepository.findByUserIdAndPostId(userId, postId)
                .orElseThrow { RuntimeException() }
        val post = postRepository.findById(postId)
                .orElseThrow { RuntimeException() }

        if (like.isLike()) {
            post.decreaseLikeCnt()
        } else {
            post.decreaseDislikeCnt()
        }

        likeRepository.delete(like)
        return like.likeType
    }
}