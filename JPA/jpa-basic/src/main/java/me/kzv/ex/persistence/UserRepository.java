package me.kzv.ex.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/*
 https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repository-query-keywords

 */

public interface UserRepository extends JpaRepository<User, Long> {
    // 다양한 select문 ... 다양한 자료형으로 리턴 가능하다.
    List<User> findByName(String name); // 동명이인이라면 반환타입이 User하나만 가져올 경우 에러가 난다.

    Optional<User> findByEmail(String email);

    Set<User> findUserByEmail(String email);

    User getByEmail(String email);

    User readByEmail(String email);

    User queryByEmail(String email);

    User searchByEmail(String email);

    User streamByEmail(String email);

    User findFirst1ByName(String name);

    // 상위 2개
    List<User> findTop2ByName(String name);

    // And 조건
    List<User> findByEmailAndName(String email, String name);
    // Or 조건
    List<User> findByEmailOrName(String email, String name);

    // After, Before키워드는 숫자나 시간을 비교할때 사용하는게 좋다
    List<User> findByCreatedAtAfter(LocalDateTime yesterday); // > 큰것들
    // Before, After와 같은 쿼리를 실행하나 다양한 자료형에 대응이 더 편한듯?
    List<User> findByCreatedAtGreaterThan(LocalDateTime yesterday);
    // <= (이하) >= (이상)  조건 사용가능
    List<User> findByCreatedAtGreaterThanEqual(LocalDateTime yesterday);

    List<User> findByIdAfter(Long id);

    // 날짜 기간 검색시 사용 편함 (Between = 이상 ~ 이하)
    // where user0_.created_at between ? and ?
    List<User> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
    // where user0_.created_at>=? and user0_.created_at<=?
    List<User> findByCreatedAtGreaterThanEqualAndCreatedAtLessThanEqual(LocalDateTime startDate, LocalDateTime endDate);

    // 빈 값 조회
    List<User> findByIdIsNotNull(); // -- 문자열을 비교함

    // adress is not null and adress !='' ?
    List<User> findByAddressIsNotEmpty(); //can't not create query,,, can only be used on collection properties -- 문자열의 비교가 아닌 컬렉션 타입의 비교를 해준다.

    // where user0_.name in (? , ?)
    List<User> findByNameIn(List<String> names);

    List<User> findByNameStartingWith(String name);

    List<User> findByNameEndingWith(String name);

    List<User> findByNameContains(String name);

    List<User> findByNameLike(String name); // % 검색어 % 형태로 넣어줘야되서 위와 같은 메서드가 탄생했다.

    // 심화 조회
    // DESC 내림차순 ASC 오름차순
    List<User> findTop2ByNameOrderByIdDesc(String name);

    List<User> findFirstByNameOrderByIdDescEmailAsc(String name);

    List<User> findFirstByName(String name, Sort sort);

    // 페이징 처리
    Page<User> findByName(String name, Pageable pageable);

}

