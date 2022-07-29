package me.kzv.shopapi.repository;

import me.kzv.shopapi.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository // 그냥 JPA 상속받으면 필요 없음
public class MemberRepository {

    @PersistenceContext // 엔티티메니저를 알아서 스프링부트가 주입시켜줌 <- 생성자 주입하면 필요없긴함
    private EntityManager entityManager;

//    @PersistenceUnit // 팩토리를 직접 주입 받을 수 잇음
//    private EntityManagerFactory entityManagerFactory;

    public Long save(Member member) {
        entityManager.persist(member);
        return member.getId();
    }

    // 단건 조회
    public Member findOne(Long id) {
        return entityManager.find(Member.class, id);
    }

    // 리스트 조회
    public List<Member> findAll(){
        return entityManager.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    // 이름으로 조회
    public List<Member> findByName(String name) {
        return entityManager.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
