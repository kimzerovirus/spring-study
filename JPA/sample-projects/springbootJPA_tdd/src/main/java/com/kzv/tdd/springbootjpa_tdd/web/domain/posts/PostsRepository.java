package com.kzv.tdd.springbootjpa_tdd.web.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface PostsRepository extends JpaRepository<Posts, Long> {
    // Entity클래스는 EntityRepository와 함께 위치해야된다. 엔티티는 기본 레포지토리 없이는 제대로 작동 할 수 없다.
    // @Repository가 별도로 필요없이 JpaRepository<엔티티클래스, PK타입>을 상속받으면 된다.

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
