package me.kzv.datajpa.repository;

import me.kzv.datajpa.dto.MemberDto;
import me.kzv.datajpa.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, CustomMemberRepository  {
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

    @Query("select m from Member m left join fetch m.team")
    List<Member> findMemberFetchJoin();

    @Override // jpa 상위에 있는 findAll 을 사용하는 법
    @EntityGraph(attributePaths = {"team"}) // 내부적으로 fetch join 사용함
    List<Member> findAll();

    @EntityGraph(attributePaths = {"team"})
    @Query("select m from Member m")
    List<Member> findMemberEntityGraph();

//    @EntityGraph(attributePaths = {"team"})
    // NamedEntityGraph 를 사용하면 아래와 같이 추가해주면 된다.
    @EntityGraph("Member.all")
    List<Member> findEntityGraphByUsername(@Param("username") String username);

    @QueryHints(value = @QueryHint(name = "org.hibernate.readOnly", value = "true"))
    Member findReadOnlyByUsername(String username);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<Member> findLockByUsername(String username);

    List<UsernameOnly> findProjectionByUsername(@Param("username") String username); // 파라미터 명이 중요

    <T> List<T> findProjectionsByUsername(@Param("username") String username, Class<T> type); // 파라미터 명이 중요

    // 네이티브 쿼리는 최후의 수단으로 사용하자! 반환타입 지원도 부실하고 Sort 가 제대로 작동 안할 수도 있고 동적쿼리도 안됨 -> jdbcTemplate 이 차라리 나음
    @Query(value = "select * from member where username = ?", nativeQuery = true)
    Member findByNativeQuery(String username);

    // 프로젝션과 쓰면 쓸만할지도?
    @Query(value = "select m.member_id as id, m.username, t.name as teamName " +
            "from member m left join team t",
            countQuery = "select count(*) from member",
            nativeQuery = true)
    Page<MemberProjection> findByNativeProjection(Pageable pageable);
}
