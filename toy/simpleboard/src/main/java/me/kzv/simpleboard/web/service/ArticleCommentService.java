package me.kzv.simpleboard.web.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.kzv.simpleboard.web.controller.dto.ArticleCommentDto;
import me.kzv.simpleboard.web.domain.Article;
import me.kzv.simpleboard.web.domain.ArticleComment;
import me.kzv.simpleboard.web.domain.UserAccount;
import me.kzv.simpleboard.web.repository.ArticleCommentRepository;
import me.kzv.simpleboard.web.repository.ArticleRepository;
import me.kzv.simpleboard.web.repository.UserAccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ArticleCommentService {
    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;
    private final UserAccountRepository userAccountRepository;


    /**
     * 댓글 리스트 가져오기
     */
    @Transactional(readOnly = true)
    public List<ArticleCommentDto> searchArticleComments(Long articleId) {
        return articleCommentRepository.findByArticle_Id(articleId)
                .stream().map(ArticleCommentDto::from) // dto -> ArticleCommentDto.from (dto) ** method reference **
                .toList();
    }

    /**
     * 댓글 저장하기
     */
    @Transactional
    public void saveArticleComment(ArticleCommentDto dto) {
        try {
            Article article = articleRepository.getReferenceById(dto.articleId());
            UserAccount userAccount = userAccountRepository.getReferenceById(dto.userAccountDto().userId());
            ArticleComment articleComment = dto.toEntity(article, userAccount);

            if (dto.parentCommentId() != null) {
                ArticleComment parentComment = articleCommentRepository.getReferenceById(dto.parentCommentId());
                parentComment.addChildComment(articleComment);
            } else {
                articleCommentRepository.save(articleComment);
            }
        } catch (EntityNotFoundException e) {
            log.warn("댓글 저장 실패, 댓글 작성에 필요한 정보 찾을 수 없음 - {}", e.getLocalizedMessage());
        }
    }

    /**
     * 댓글 삭제하기
     */
    @Transactional
    public void deleteArticleComment(Long articleCommentId, String userId) {
        articleCommentRepository.deleteByIdAndUserAccount_UserId(articleCommentId, userId);
    }
}
