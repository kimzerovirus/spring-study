package me.kzv.helloquerydsl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import me.kzv.helloquerydsl.dto.MemberDto;
import me.kzv.helloquerydsl.dto.QMemberDto;
import me.kzv.helloquerydsl.dto.UserDto;
import me.kzv.helloquerydsl.entity.Member;
import me.kzv.helloquerydsl.entity.QMember;
import me.kzv.helloquerydsl.entity.Team;
import org.assertj.core.api.Assertions;
import org.hibernate.tool.schema.spi.SchemaManagementTool;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

import static me.kzv.helloquerydsl.entity.QMember.*;
import static me.kzv.helloquerydsl.entity.QTeam.team;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
public class QueryDslBasicTest {

    @Autowired
    EntityManager em;

    JPAQueryFactory queryFactory;

    @BeforeEach
    public void before() {
        queryFactory = new JPAQueryFactory(em);

        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);

        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);
    }

    @Test
    public void JPQL_테스트_작성() throws Exception {
        Member findMember = em.createQuery("select m from Member m where m.username = :username", Member.class)
                .setParameter("username", "member1")
                .getSingleResult();

        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    @Test
    public void QueryDsl_테스트_작성() throws Exception {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);
        QMember m = new QMember("m"); // 제공하는게 있어서 안써도 됨

        Member findMember = jpaQueryFactory
                .select(m)
                .from(m)
                .where(m.username.eq("member1"))
                .fetchOne();

        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    // 멀티쓰레드에서 접근하였을 때 동시성 문제에 대한 해결책이 다 설계 되어 있으므로 쿼리 팩토리를 필드로 빼도 된다.
    @Test
    public void QueryDsl_테스트_작성2() throws Exception {
//        QMember m = QMember.member; -> static import

        Member findMember = queryFactory
                .select(member)
                .from(member)
                .where(member.username.eq("member1"))
                .fetchOne();

        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    @Test
    public void 검색_테스트_체인_형태() throws Exception {
        Member findMember = queryFactory
                .selectFrom(member)
                .where(
                        member.username.eq("member1")
                                .and(member.age.eq(10))
                )
//                        .and(QMember.member.age.between(10,30)))
                .fetchOne();

        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    @Test
    public void 검색_테스트_파라미터_형태() throws Exception {
        Member findMember = queryFactory
                .selectFrom(QMember.member)
                .where(
                        member.username.eq("member1"),
                        member.age.eq(10)
                )
                .fetchOne();

        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    @Test
    public void 결과조회() throws Exception {
        List<Member> fetch = queryFactory
                .selectFrom(member)
                .fetch();

        Member fetchOne = queryFactory
                .selectFrom(QMember.member)
                .where(
                        member.username.eq("member1"),
                        member.age.eq(10)
                )
                .fetchOne();

        Member fetchFirst = queryFactory
                .selectFrom(QMember.member)
                .fetchFirst();

        QueryResults<Member> results = queryFactory
                .selectFrom(member)
                .fetchResults();

        results.getTotal();
        List<Member> content = results.getResults();

        long total = queryFactory
                .selectFrom(member)
                .fetchCount();
        /**
         fetchCount() , fetchResult() 는 개발자가 작성한 select 쿼리를 기반으로 count 용 쿼리를 내부에서 만들어서 실행한다.
         select 구문을 단순히 count 처리하는 용도로 바꾸는 정도아다. 따라서 단순한 쿼리에서는 잘 동작하지만, 복잡한 쿼리에서는 제대로 동작하지 않는다.
         */
        /*
            다음과 같이 카운트 쿼리를 별도로 만들어서 사용한다.
                Long totalCount = queryFactory
                      //.select(Wildcard.count) //select count(*)
                      .select(member.count()) //select count(member.id)
                      .from(member)
                      .fetchOne();
         */
    }

    /**
     * 회원 정렬 순서
     * 1. 회원 나이 내림차순 (desc)
     * 2. 회원 나이 올림차순 (asc)
     * 단, 2에서 회원 이름이 없으면 마지막에 출력 (nulls last)
     */
    @Test
    public void 정렬() throws Exception {
        em.persist(new Member(null, 100));
        em.persist(new Member("member5", 100));
        em.persist(new Member("member6", 100));

        List<Member> result = queryFactory
                .selectFrom(member)
                .where(member.age.eq(100))
                .orderBy(member.age.desc(), member.username.asc().nullsLast()) // null 은 맨 뒤로
                .fetch();

        Member member5 = result.get(0);
        Member member6 = result.get(1);
        Member memberNull = result.get(2);

        assertThat(member5.getUsername()).isEqualTo("member5");
        assertThat(member6.getUsername()).isEqualTo("member6");
        assertThat(memberNull.getUsername()).isNull();
    }

    @Test
    public void 페이징() throws Exception {
        List<Member> result = queryFactory
                .selectFrom(member)
                .orderBy(member.username.desc())
                .offset(1)
                .limit(2)
                .fetch();

        assertThat(result.size()).isEqualTo(2);

        QueryResults<Member> fetchResults = queryFactory // 카운트 쿼리가 나가고 그 다음에 컨텐츠 용 쿼리가 나간다 // where 조건 붙고 하면 양쪽 join 에 붙어서 성능상 이슈가 발생할 수 있으므로 그럴 경우에는 카운트 쿼리를 따로 만드는게 낫다.
                .selectFrom(member)
                .orderBy(member.username.desc())
                .offset(1)
                .limit(2)
                .fetchResults();

        assertThat(fetchResults.getTotal()).isEqualTo(4);
        assertThat(fetchResults.getLimit()).isEqualTo(2);
        assertThat(fetchResults.getOffset()).isEqualTo(1);
        assertThat(fetchResults.getResults().size()).isEqualTo(2);

    }

    @Test
    public void 집합() throws Exception {
        List<Tuple> result = queryFactory // querydsl 에서 제공하는 튜플임!!
                .select(
                        member.count(),
                        member.age.sum(),
                        member.age.avg(),
                        member.age.max(),
                        member.age.min()
                )
                .from(member)
                .fetch();

        Tuple tuple = result.get(0);
        assertThat(tuple.get(member.count())).isEqualTo(4);
        assertThat(tuple.get(member.age.sum())).isEqualTo(100);
        assertThat(tuple.get(member.age.avg())).isEqualTo(25);
        assertThat(tuple.get(member.age.max())).isEqualTo(40);
        assertThat(tuple.get(member.age.min())).isEqualTo(10);
    }

    /**
     * 팀의 이름과 각 팀의 평균 연령 구하기
     */
    @Test
    public void 그룹조회() throws Exception {
        List<Tuple> result = queryFactory
                .select(team.name, member.age.avg())
                .from(member)
                .join(member.team, team)
                .groupBy(team.name) // having 도 가능
                .fetch();

        Tuple teamA = result.get(0);
        Tuple teamB = result.get(1);

        assertThat(teamA.get(team.name)).isEqualTo("teamA");
        assertThat(teamA.get(member.age.avg())).isEqualTo(15);

        assertThat(teamB.get(team.name)).isEqualTo("teamB");
        assertThat(teamB.get(member.age.avg())).isEqualTo(35);
    }

    /**
     * 팀 A에 소속된 모든 회원
     */
    @Test
    public void 조인조회() throws Exception {
        List<Member> result = queryFactory
                .selectFrom(member)
                .join(member.team, team) // leftJoin .. thetaJoin 이라고 연관관계 없이도 조인 하는 기능을 지원
                .where(team.name.eq("teamA"))
                .fetch();

        assertThat(result)
                .extracting("username")
                .containsExactly("member1", "member2");
    }

    /**
     * 세타 조인
     * 회원의 이름이 팀 이름과 같은 회원 조회
     */
    @Test
    public void theta_join() throws Exception {
        em.persist(new Member("teamA"));
        em.persist(new Member("teamB"));

        List<Member> result = queryFactory
                .select(member)
                .from(member, team)
                .where(member.username.eq(team.name))
                .fetch();

        // 모든 회원과 팀을 가져온다음에 조인해서 where 해서 찾아옴

        assertThat(result)
                .extracting("username")
                .containsExactly("teamA", "teamB");

        // 외부 조인이 불가능 하지만 -> 조인 on 기능을 사용하면 외부 조인도 가능하다.
    }

    /**
     * 예) 회원과 팀을 조인하면서, 팀 이름이 teamA인 팀만 조인, 회원은 모두 조회
     * JPQL : select m, t from Member m left join m.team t on t.name = 'teamA'
     */
    @Test
    public void join_on_필터링() throws Exception {
        List<Tuple> result = queryFactory
                .select(member, team)
                .from(member)
                .leftJoin(member.team, team)
                .on(team.name.eq("teamA"))
                .fetch();

        // member 3, 4 는 팀이 null fh ehla
        for (Tuple tuple : result) {
            System.out.println("Tuple = " + tuple);
        }
    }

    /**
     * 연관관계가 없는 엔티티를 외부 조인
     * 회원의 이름과 팀 이름이 같은 대상을 외부 조인
     */
    @Test
    public void join_on_no_relation() throws Exception {
        em.persist(new Member("teamA"));
        em.persist(new Member("teamB"));

        List<Tuple> result = queryFactory
                .select(member, team)
                .from(member)
                .leftJoin(team).on(member.username.eq(team.name)) // 그냥 join 이면 null 데이터는 안나오겠지?
                .fetch();

        // 멤버와 팀이름이 같으면 같이 가져오고 아니면 null
        for (Tuple tuple : result) {
            System.out.println("Tuple = " + tuple);
        }
    }

    @PersistenceUnit
    EntityManagerFactory emf;

    @Test
    public void fetchJoin() throws Exception {
        em.flush();
        em.clear();

        Member findMember = queryFactory
                .selectFrom(member)
                .join(member.team, team).fetchJoin()
                .where(member.username.eq("member1"))
                .fetchOne();

        System.out.println("findMember = " + findMember);

        boolean loaded = emf.getPersistenceUnitUtil().isLoaded(findMember.getTeam());// 초기화가 안된 엔티티 인지 알려줌
//        assertThat(loaded).as("패치 조인 미적용").isFalse(); // 중간에 터치하면 로딩이 되겠지만
        assertThat(loaded).as("패치 조인 적용").isTrue();
    }

    /**
     * 나이가 가장 많은 회원 조회
     */
    @Test
    public void subQuery() throws Exception {
        QMember memberSub = new QMember("memberSub");

        List<Member> result = queryFactory
                .selectFrom(member)
                .where(member.age.eq(
                        JPAExpressions
                                .select(memberSub.age.max())
                                .from(memberSub) // alias 다른 것은 q객체 이름 다르게 만들어줘야됨
                ))
                .fetch();

        assertThat(result).extracting("age")
                .containsExactly(40);
    }

    /**
     * 나이가 평균 이상인 회원
     */
    @Test
    public void subQuery_goe() throws Exception {
        QMember memberSub = new QMember("memberSub");

        List<Member> result = queryFactory
                .selectFrom(member)
                .where(member.age.goe(
                        JPAExpressions
                                .select(memberSub.age.avg())
                                .from(memberSub) // alias 다른 것은 q객체 이름 다르게 만들어줘야됨
                ))
                .fetch();

        assertThat(result).extracting("age")
                .containsExactly(30, 40);
    }

    /**
     * 나이가 평균 이상인 회원 - 억지 예제ㅋㅋ
     */
    @Test
    public void subQuery_in() throws Exception {
        QMember memberSub = new QMember("memberSub");

        List<Member> result = queryFactory
                .selectFrom(member)
                .where(member.age.in(
                        JPAExpressions
                                .select(memberSub.age)
                                .from(memberSub) // alias 다른 것은 q객체 이름 다르게 만들어줘야됨
                                .where(memberSub.age.gt(10))
                ))
                .fetch();

        assertThat(result).extracting("age")
                .containsExactly(20, 30, 40);
    }

    @Test
    public void selectSubQuery() throws Exception {
        QMember memberSub = new QMember("memberSub");

        List<Tuple> result = queryFactory
                .select(
                        member.username,
                        JPAExpressions // static import 해버리자
                                .select(memberSub.age.avg())
                                .from(memberSub)
                )
                .from(member)
                .fetch();

        for (Tuple tuple : result) {
            System.out.println("Tuple = " + tuple);
        }

        // JPA JPQL 서브쿼리의 한계점으로 from 절의 서브쿼리(인라인 뷰)는 지원하지 않는다. 당연히 Qeurydsl 또한 지원하지 않는다.

        // from 절의 서브쿼리 해결방안
        // 1. join 으로 해결하기
        // 2. 쿼리 분리해서 날리기
        // 3. 네이티브 SQL 사용하기
    }

    @Test
    public void basicCase() throws Exception {
        List<String> result = queryFactory
                .select(
                        member.age
                                .when(10).then("열살")
                                .when(20).then("스무살")
                                .otherwise("기타")
                )
                .from(member).fetch();

        for (String s : result) {
            System.out.println("s = " + s);
        }
    }

    @Test
    public void complexCase() throws Exception {
        List<String> result = queryFactory
                .select(
                        new CaseBuilder()
                                .when(member.age.between(0, 20)).then("0~20살")
                                .when(member.age.between(21, 30)).then("21살~30살")
                                .otherwise("기타")
                )
                .from(member).fetch();

        for (String s : result) {
            System.out.println("s = " + s);
        }
    }

    @Test
    public void constant() throws Exception {
        List<Tuple> result = queryFactory
                .select(member.username, Expressions.constant("A"))
                .from(member)
                .fetch();

        for (Tuple tuple : result) {
            System.out.println("Tuple = " + tuple);
        }
    }

    /**
     * # CONCAT
     * {username}_{age}
     */
    @Test
    public void concat() throws Exception {
        List<String> result = queryFactory
                .select(member.username.concat("_").concat(member.age.stringValue()))
                .from(member)
                .where(member.username.eq("member1"))
                .fetch();

        for (String s : result) {
            System.out.println("s = " + s);
        }
    }

    @Test
    public void simpleProjection() throws Exception {
        List<String> result = queryFactory
                .select(member.username)
                .from(member)
                .fetch();

        for (String s : result) {
            System.out.println("s = " + s);
        }
    }

    @Test
    public void tupleProjection() throws Exception {
        // tuple 은 querydsl 에서 사용하는것이므로 리포지토리를 넘어가는 계층에서는 안쓰는게 좋을듯

        List<Tuple> result = queryFactory
                .select(member.username, member.age)
                .from(member)
                .fetch();

        for (Tuple tuple : result) {
            System.out.println("username = " + tuple.get(member.username));
            System.out.println("age = " + tuple.get(member.age));
        }
    }

    @Test
    public void findDtoBy_JPQL() throws Exception {
        List<MemberDto> result = em.createQuery("select new me.kzv.helloquerydsl.dto.MemberDto(m.username, m.age) from Member m", MemberDto.class)
                .getResultList();

        for (MemberDto dto : result) {
            System.out.println("dto = " + dto);
        }
    }

    @Test
    public void findDtoBySetter_Querydsl() throws Exception {
        List<MemberDto> result = queryFactory
                .select(Projections.bean(MemberDto.class, // getter setter constructor - 기본 생성자를 생성해서 set 하는 방식
                        member.username,
                        member.age))
                .from(member)
                .fetch();

        for (MemberDto dto : result) {
            System.out.println("dto = " + dto);
        }
    }

    @Test
    public void findDtoByField_Querydsl() throws Exception {
        List<MemberDto> result = queryFactory
                .select(Projections.fields(MemberDto.class, // 필드에 바로 꽂아버린다
                        member.username,
                        member.age))
                .from(member)
                .fetch();

        for (MemberDto dto : result) {
            System.out.println("dto = " + dto);
        }
    }

    @Test
    public void findDtoByConstructor_Querydsl() throws Exception {
        List<MemberDto> result = queryFactory
                .select(Projections.constructor(MemberDto.class, // 생성자를 생성
                        member.username,
                        member.age))
                .from(member)
                .fetch();

        for (MemberDto dto : result) {
            System.out.println("dto = " + dto);
        }
    }

    @Test
    public void findUserDto() {
        QMember memberSub = new QMember("memberSub");

        List<UserDto> result = queryFactory
                .select(Projections.fields(UserDto.class,
                        member.username.as("name"), // dto 와 entity 의 이름이 다른 경우

                        ExpressionUtils.as(
                                JPAExpressions
                                        .select(memberSub.age.max())
                                        .from(memberSub), "age")))
                .from(member)
                .fetch();

        for (UserDto dto : result) {
            System.out.println("dto = " + dto);
        }

        List<UserDto> resultConstructor = queryFactory
                .select(Projections.constructor(UserDto.class, // 생성자를 생성
                        member.username,
                        member.age))
                .from(member)
                .fetch();

        for (UserDto dto : resultConstructor) {
            System.out.println("dto = " + dto);
        }
    }

    /**
     * 위의 코드는 컴파일 시점에서 더많은 인자를 할당하거나 해도 컴파일 오류가 안나는 문제가 있다
     * dto 를 생성하는 데에 더 편한 방법 dto 에 @QueryProjection 어노테이션을 추가
     * 다만, dto 가 querydsl 을 의존하게 된다.
     */
    @Test
    public void findDtoByQueryProjection() throws Exception {
        List<MemberDto> result = queryFactory
                .select(new QMemberDto(member.username, member.age))
                .from(member)
                .fetch();


        for (MemberDto dto : result) {
            System.out.println("dto = " + dto);
        }
    }

    /**
     * 동적쿼리
     * 1. BooleanBuilder
     * 2. where 조건 안에서 다중 파라미터로 해결
     */
    @Test
    public void dynamicQuery_BooleanBuilder() throws Exception {
        String usernameParam = "member1";
        Integer ageParam = 10;

        List<Member> result = searchMember1(usernameParam, ageParam);
        assertThat(result.size()).isEqualTo(1);
    }

    private List<Member> searchMember1(String usernameParam, Integer ageParam) {
        BooleanBuilder builder = new BooleanBuilder();

        if (usernameParam != null) {
            builder.and(member.username.eq(usernameParam));
        }

        if (ageParam != null) {
            builder.and(member.age.eq(ageParam));
        }

        return queryFactory
                .selectFrom(member)
                .where(builder)
                .fetch();
    }

    @Test
    public void dynamicQuery_WhereParam() {
        String usernameParam = "member1";
        Integer ageParam = 10;

        List<Member> result = searchMember2(usernameParam, ageParam);
        assertThat(result.size()).isEqualTo(1);
    }

    private List<Member> searchMember2(String usernameParam, Integer ageParam) {
        return queryFactory
                .selectFrom(member)
//                .where(usernameEq(usernameParam), ageEq(ageParam)) // null 일 경우에 무시하고 지나간다.
                .where(allEq(usernameParam, ageParam))
                .fetch();
    }

    private Predicate usernameEq(String usernameParam) {
        if (usernameParam == null) {
            return null;
        } else {
            return member.username.eq(usernameParam);
        }
    }

    private Predicate ageEq(Integer ageParam) {
        return ageParam != null ? member.age.eq(ageParam) : null;
    }

    // 위의 2개를 하나로 묶어버리기
    private BooleanExpression allEq(String usernameCond, Integer ageCond) {
        return usernameOneEq(usernameCond).and(ageOneEq(ageCond));
    }

    private BooleanExpression usernameOneEq(String usernameParam) {
        return usernameParam != null ? member.username.eq(usernameParam) : null;
    }

    private BooleanExpression ageOneEq(Integer ageParam) {
        return ageParam != null ? member.age.eq(ageParam) : null;
    }

    @Test
    public void bulkUpdate() {

        // member1 = 10 ->  비회원
        // member2 = 20 ->  비회원
        // member3 = 30 ->  유지
        // member4 = 40 ->  유지

        long result = queryFactory
                .update(member)
                .set(member.username, "비회원")
                .where(member.age.lt(28))
                .execute(); // 벌크 연산은 쿼리가 바로 날라가서 DB 와 영속성 컨텍스트 간의 괴리가 발생!

        // 그런데 수정 후에 select 했을 때 영속성 컨텍스트가 남아 있다면 우선권을 가지므로 업데이트 되기 전의 데이터가 출력된다.

        List<Member> list = queryFactory
                .selectFrom(member)
                .fetch();

        for (Member member : list) {
            System.out.println("Member = " + member);
        }
        /*
            Member = Member(id=3, username=member1, age=10)
            Member = Member(id=4, username=member2, age=20)
            Member = Member(id=5, username=member3, age=30)
            Member = Member(id=6, username=member4, age=40)
         */

        // ------------------------ 초기화 ---------------------------
        em.flush();
        em.clear();

        List<Member> reList = queryFactory
                .selectFrom(member)
                .fetch();

        for (Member member : reList) {
            System.out.println("Member = " + member);
        }

        /*

            Member = Member(id=3, username=비회원, age=10)
            Member = Member(id=4, username=비회원, age=20)
            Member = Member(id=5, username=member3, age=30)
            Member = Member(id=6, username=member4, age=40)
        */

    }

    @Test
    public void bulkAdd() {
        long count = queryFactory
                .update(member)
                .set(member.age, member.age.add(1))
                .execute();
    }

    @Test
    public void bulkDelete() {
        queryFactory
                .delete(member)
                .where(member.age.gt(18))
                .execute();
    }

    /**
     * 비교 표현식 (gt, lt, ge, le, eq, ne)
     * | 크다 | a gt b | a > b |
     * | 작다 | a lt b | a < b |
     * | 크거나 같다 | a ge b | a >= b |
     * | 작거나 같다 | a le b  | a <= b |
     * | 같다 | a eq b | a == b |
     * | 같지 않다 | a ne b | a !== b |
     */

    @Test
    public void sqlFunction() throws Exception {
        List<String> result = queryFactory
                .select(Expressions.stringTemplate(
                        "function('replace',{0},{1},{2})",
                        member.username, "member", "M"))
                .from(member)
                .fetch();


    }

    @Test
    public void sqlFunction2(){
        List<String> result = queryFactory
                .select(member.username)
                .from(member)
                .where(member.username.eq(
//                        Expressions.stringTemplate("function('lower',{0})", member.username)
                        member.username.lower() // ANSI 표준에 있는 함수는 querydsl 이 자체 제공한다.
                ))
                .fetch();

        for (String s : result) {
            System.out.println("s = " + s);
        }
    }

}
