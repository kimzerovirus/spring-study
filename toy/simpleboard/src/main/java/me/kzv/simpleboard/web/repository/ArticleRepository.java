package me.kzv.simpleboard.web.repository;

import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import me.kzv.simpleboard.web.domain.Article;
import me.kzv.simpleboard.web.domain.QArticle;
import me.kzv.simpleboard.web.repository.querydsl.CustomArticleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface ArticleRepository extends
        JpaRepository<Article, Long>,
        CustomArticleRepository,
        QuerydslPredicateExecutor<Article>,
        QuerydslBinderCustomizer<QArticle>
{
    @Override
    default void customize(QuerydslBindings bindings, QArticle root){
        // 선택적 검색
        bindings.excludeUnlistedProperties(true);
        // 선택값
        bindings.including(root.title, root.content, root.hashtags, root.createdDate, root.createdBy);
//        bindings.bind(root.title).first(StringExpression::likeIgnoreCase); // like '${v}' ((path, value) -> path.eq(value))
//        bindings.bind(root.content).first(StringExpression::containsIgnoreCase); // like '%${v}%' ((path, value) -> path.eq(value))
//        bindings.bind(root.hashtag).first(StringExpression::containsIgnoreCase);

        bindings.bind(root.title).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.hashtags.any().hashtagName).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.createdDate).first(DateTimeExpression::eq);
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);
    }

    Page<Article> findByTitleContaining(String title, Pageable pageable);
    Page<Article> findByContentContaining(String content, Pageable pageable);
    Page<Article> findByUserAccount_UserIdContaining(String userId, Pageable pageable);
    Page<Article> findByUserAccount_NicknameContaining(String nickname, Pageable pageable);

    void deleteByIdAndUserAccount_UserId(Long articleId, String userId);
}
