package me.kzv.ecommerce.repository;

import me.kzv.ecommerce.entity.Item;
import me.kzv.ecommerce.entity.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    // https://www.inflearn.com/questions/38771/querydsl%EA%B3%BC-jpql%EC%9D%84-%EC%84%A0%ED%83%9D%ED%95%98%EB%8A%94-%EC%B0%A8%EC%9D%B4%EA%B0%80-%EA%B6%81%EA%B8%88%ED%95%A9%EB%8B%88%EB%8B%A4
    // querydsl 동적쿼리인 경우 사용
    // 단순한거 하나 둘이면 귀찮으니깐 jpql 쓰자
    @Query("select o from Order o where o.member.email = :email order by o.orderDate desc")
    List<Order> findOrders(@Param("email") String email, Pageable pageable);

    @Query("select count(o) from Order o where o.member.email = :email")
    Long countOrder(@Param("email") String email);

}
