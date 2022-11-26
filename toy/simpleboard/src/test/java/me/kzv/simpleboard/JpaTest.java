//package me.kzv.simpleboard;
//
//
//import me.kzv.simpleboard.config.JpaConfig;
//import me.kzv.simpleboard.web.domain.Article;
//import me.kzv.simpleboard.web.domain.ArticleComment;
//import me.kzv.simpleboard.web.repository.ArticleCommentRepository;
//import me.kzv.simpleboard.web.repository.ArticleRepository;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.context.annotation.Import;
//import org.springframework.test.annotation.Rollback;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@DisplayName("JPA 연결 테스트")
//@Import(JpaConfig.class)
//@DataJpaTest // rollback 돌림
//@Rollback
//class JpaTest {
//
//    @Autowired ArticleRepository articleRepository;
//    @Autowired ArticleCommentRepository articleCommentRepository;
//
//    @DisplayName("조회 테스트")
//    @Test
//    public void select_test() throws Exception {
//        //given
//
//        //when
//        List<Article> articles = articleRepository.findAll();
//
//        //then
//        assertThat(articles)
//        .isNotNull()
//        .hasSize(0);
//    }
//
//    @DisplayName("글 추가 테스트")
//    @Test
//    public void insert_test() throws Exception {
//        //given
//        long previousCount = articleRepository.count();
//        Article article = Article.of("new article", "new content", "new hashtag");
//
//        //when
//        Article savedArticle = articleRepository.save(article);
//
//        //then
//        assertThat(articleRepository.count()).isEqualTo(previousCount + 1);
//
//    }
//
//
//    @DisplayName("글 수정 테스트")
//    @Test
//    public void update_test() throws Exception {
//        //given
//        long previousCount = articleRepository.count();
//        Article originArticle = Article.of("new article", "new content", "new hashtag");
//        Article savedArticle = articleRepository.save(originArticle);
//        savedArticle.setContent("updated");
//
//        //when
//        Article updatedArticle = articleRepository.saveAndFlush(savedArticle);
//
//        //then
//        assertThat(updatedArticle).hasFieldOrPropertyWithValue("content","updated");
////        assertThat(originArticle.getContent()).isNotEqualTo(updatedArticle.getContent());
//    }
//
//    @DisplayName("글 삭제 테스트")
//    @Test
//    public void delete_test() throws Exception {
//        //given
////        long previousArticleCommentCount = articleCommentRepository.count();
////        long previousArticleCount = articleRepository.count();
////
////        Article article = Article.of("new article", "new content", "new hashtag");
////        articleRepository.saveAndFlush(article);
////        ArticleComment articleComment = ArticleComment.of(article, "new comment");
////        articleCommentRepository.saveAndFlush(articleComment);
////
////
////        //when
////        articleRepository.delete(article);
////        articleCommentRepository.delete(articleComment);
////
////        //then
//
//    }
//}
