package me.kzv.datajpa.repository;

import me.kzv.datajpa.dto.MemberDto;
import me.kzv.datajpa.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    // NamedQuery 사용하기
//    @Query(name = "Member.findByUsername") <- 없어도 상속할 때 선언한 Member 클래스를 기준으로 찾아온다.
    List<Member> findByUsername(@Param("username") String username); // JPQL 에서 :username 과 같이 변수를 할당한 경우 @Param 어노테이션을 써주어야 한다.

    // JPQL
    @Query("select m from Member m where m.username = :username and m.age = :age")
    List<Member> findUser(@Param("username") String username, @Param("age") int age);

    @Query("select m.username from Member m")
    List<String> findUsernameList();

    // dto 객체로 조회하기
    @Query("select new me.kzv.datajpa.dto.MemberDto(m.id, m.username, t.name) from Member m join m.team t")
    List<MemberDto> findMemberDto();

    @Query("select m from Member m where m.username in :names")
    List<Member> findByNames(@Param("names") Collection<String> names); // @Param 어노테이션을 안 쓰면 Use @Param for query method parameter 와 같은 에러 문구가 나온다.

    List<Member> findListByUsername(String username); // 컬렉션

    Member findMemberByUsername(String username); // 단건

    // find 와 by 사이에 아무거나 써넣어도 된다.
    Optional<Member> findOptionByUsername(String username); // 단건 Optional

    // 페이징 처리
    // Defines a special count query that shall be used for pagination queries to lookup the total number of elements for a page.
    @Query(value = "select m from Member m left join m.team t", countQuery = "select count(m.username) from Member m")
    Page<Member> findByAge(int age, Pageable pageable);

    @Modifying // executeUpdate 를 실행하기 위해 넣어 줘야한다. 안넣어주면 JPA 가 singleResult 같은 것을 실행함
//    @Modifying(clearAutomatically = true) // entitymanger flush & clear ON
    @Query("update Member m set m.age = m.age + 1 where m.age >= :age")
    int bulkAgePlus(@Param("age") int age);
}
