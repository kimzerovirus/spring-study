package me.kzv.shop.domain;

import me.kzv.shop.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Rollback(value = false) // rollback false 해서 테스트가 끝나고 데이터가 지워지지 않게 한다.
    @Transactional // 모든 엔티티메니저는 트랜잭션 안에서 작동해야 된다. 자바 기본 보다는 스프링것을 추천
    public void 멤버테스트() throws Exception {
        //given
        Member member = new Member();
        member.setUsername("memberA");

        //when
        Long saveId = memberRepository.save(member);
        Member findMember = memberRepository.find(saveId);

        //then
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        Assertions.assertThat(findMember).isEqualTo(member);
        System.out.println("findMember == member: " + (findMember == member));
        /*
            1차 캐시하고 있는 거에서 꺼내와서 비교하고 같네하고 끝내므로 select 까지도 안가고 insert 에서 끝냄
            같은 영속성 컨텍스트 안에서는 식별자가 같으면 같은 객체
        */
    }

}