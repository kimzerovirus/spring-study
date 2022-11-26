package me.kzv.simpleboard.web.service;

import io.micrometer.core.instrument.search.Search;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.kzv.simpleboard.web.controller.dto.ArticleDto;
import me.kzv.simpleboard.web.controller.dto.ArticleWithCommentsDto;
import me.kzv.simpleboard.web.controller.dto.HashtagDto;
import me.kzv.simpleboard.web.domain.Article;
import me.kzv.simpleboard.web.domain.BaseEntity;
import me.kzv.simpleboard.web.domain.Hashtag;
import me.kzv.simpleboard.web.domain.UserAccount;
import me.kzv.simpleboard.web.domain.enums.SearchType;
import me.kzv.simpleboard.web.repository.ArticleRepository;
import me.kzv.simpleboard.web.repository.HashtagRepository;
import me.kzv.simpleboard.web.repository.UserAccountRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class ArticleService {

    // referencedById -> https://creampuffy.tistory.com/162
    // findById 와 달리 값이 필요한 시점에 가져온다(lazy loading)

    private final ArticleRepository articleRepository;
    private final UserAccountRepository userAccountRepository;
    private final HashtagService hashtagService;
    private final HashtagRepository hashtagRepository;

    /**
     * 검색 타입과 키워드에 따른 게시글 가져오기
     */
    @Transactional(readOnly = true)
    public Page<ArticleDto> searchArticles(SearchType type, String keyword, Pageable pageable) {
        if (keyword == null || keyword.isBlank()) {
            // 검색 키워드가 없으면 전체 리스트
            return articleRepository.findAll(pageable).map(ArticleDto::from);
        }

        return switch (type) {
            case TITLE -> articleRepository.findByTitleContaining(keyword, pageable).map(ArticleDto::from);
            case CONTENT -> articleRepository.findByContentContaining(keyword, pageable).map(ArticleDto::from);
            case ID -> articleRepository.findByUserAccount_UserIdContaining(keyword, pageable).map(ArticleDto::from);
            case NICKNAME -> articleRepository.findByUserAccount_NicknameContaining(keyword, pageable).map(ArticleDto::from);
            case HASHTAG -> articleRepository.findByHashtagNames(Arrays.stream(keyword.split(" ")).toList(), pageable).map(ArticleDto::from);
        };
    }

    /**
     * 게시글 + 댓글 가져오기
     */
    @Transactional(readOnly = true)
    public ArticleWithCommentsDto getArticleWithComments(Long articleId) {
        return articleRepository.findById(articleId)
                .map(ArticleWithCommentsDto::from).orElseThrow(() -> new EntityNotFoundException("게시글이 없습니다. - id: " + articleId));
    }

    /**
     * 게시글 단건
     */
    @Transactional(readOnly = true)
    public ArticleDto getArticle(Long articleId) {
        return articleRepository.findById(articleId)
                .map(ArticleDto::from)
                .orElseThrow(() -> new EntityNotFoundException("게시글이 없습니다 - id: " + articleId));
    }

    /**
     * 게시글 저장
     */
    @Transactional
    public void saveArticle(ArticleDto dto) {
        UserAccount userAccount = userAccountRepository.getReferenceById(dto.userAccountDto().userId());
        Set<Hashtag> hashtags = renewHashtagsFromContent(dto.content());

        Article article = dto.toEntity(userAccount);
        article.addHashtags(hashtags);
        articleRepository.save(article);
    }

    /**
     *  게시글 업데이트
     */
    @Transactional
    public void updateArticle(Long articleId, ArticleDto dto) {
        try {
            Article article = articleRepository.getReferenceById(articleId);
            UserAccount userAccount = userAccountRepository.getReferenceById(dto.userAccountDto().userId());

            if (article.getUserAccount().equals(userAccount)) {
                if (dto.title() != null) article.setTitle(dto.title());
                if (dto.content() != null) article.setContent(dto.content());

                Set<Long> hashtagIds = article.getHashtags().stream()
                        .map(Hashtag::getId)
                        .collect(Collectors.toUnmodifiableSet());

                hashtagIds.forEach(hashtagService::deleteHashtagWithoutArticles);

                Set<Hashtag> hashtags = renewHashtagsFromContent(dto.content());
                article.addHashtags(hashtags);
            }
        } catch (EntityNotFoundException e) {
            log.warn("게시글 업데이트 실패");
        }
    }

    /**
     * 게시글 삭제
     */
    @Transactional
    public void deleteArticle(long articleId, String userId) {
        Article article = articleRepository.getReferenceById(articleId);
        Set<Long> hashtagIds = article.getHashtags().stream()
                .map(Hashtag::getId)
                .collect(Collectors.toUnmodifiableSet());

        articleRepository.deleteByIdAndUserAccount_UserId(articleId, userId);
        articleRepository.flush(); // 게시글 삭제 완료시킨 후 해시태그도 삭제

        hashtagIds.forEach(hashtagService::deleteHashtagWithoutArticles);
    }

    @Transactional(readOnly = true)
    public long getArticleCount(){
        return articleRepository.count();
    }

    /**
     * 해시태그로 게시글 검색
     */
    @Transactional(readOnly = true)
    public Page<ArticleDto> searchArticlesViaHashtag(String hashtagName, Pageable pageable) {
        if (hashtagName == null || hashtagName.isBlank()) {
            return Page.empty(pageable);
        }

        return articleRepository.findByHashtagNames(List.of(hashtagName), pageable)
                .map(ArticleDto::from);
    }

    @Transactional(readOnly = true)
    public List<String> getHashtags(){
        return hashtagRepository.findAllHashtagNames();
    }

    private Set<Hashtag> renewHashtagsFromContent(String content) {
        Set<String> hashtagNamesInContent = hashtagService.parseHashtagNames(content);
        Set<Hashtag> hashtags = hashtagService.findHashtagsByNames(hashtagNamesInContent);
        List<String> existingHashtagNames = hashtags.stream().map(Hashtag::getHashtagName).collect(Collectors.toList());

        hashtagNamesInContent.forEach(newHashtagName ->{
            if(!existingHashtagNames.contains(newHashtagName)) hashtags.add(Hashtag.of(newHashtagName));
        });

        return hashtags;
    }
}
