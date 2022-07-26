package me.kzv.shop.repository;

import me.kzv.shop.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository // 그냥 JPA
public class MemberRepository {

    @PersistenceContext // 엔티티메니저를 알아서 스프링부트가 주입시켜줌
    private EntityManager entityManager;

    public Long save(Member member) {
        entityManager.persist(member);
        return member.getId();
    }

    public Member find(Long id) {
        return entityManager.find(Member.class, id);
    }
}
