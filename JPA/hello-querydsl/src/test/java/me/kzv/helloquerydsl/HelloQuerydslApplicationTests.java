package me.kzv.helloquerydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import me.kzv.helloquerydsl.entity.Hello;
import me.kzv.helloquerydsl.entity.QHello;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
class HelloQuerydslApplicationTests {

    @Autowired EntityManager em;

    @Test
    void contextLoads() {
        // preference 에서 gradle 검색 후 build intelliJ 로 설정 해야 테스트 실행이 더 빠름

        Hello hello = new Hello();
        em.persist(hello);

        JPAQueryFactory query = new JPAQueryFactory(em);
        QHello qHello = new QHello("h");

        Hello result = query
                .selectFrom(qHello)
                .fetchOne();

        Assertions.assertThat(result).isEqualTo(hello);
    }

}
