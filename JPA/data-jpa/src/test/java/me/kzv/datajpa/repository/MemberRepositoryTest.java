package me.kzv.datajpa.repository;

import me.kzv.datajpa.dto.MemberDto;
import me.kzv.datajpa.entity.Member;
import me.kzv.datajpa.entity.Team;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Rollback(value = false)
@Transactional
public class MemberRepositoryTest {
    @Autowired MemberRepository memberRepository;
    @Autowired TeamRepository teamRepository;
    @PersistenceContext EntityManager em;

    @Test
    public void 멤버테스트() throws Exception {
        Member member = new Member("memberA");
        Member savedMember = memberRepository.save(member);

        Member findMember = memberRepository.findById(member.getId()).get();

        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        assertThat(findMember).isEqualTo(member);
    }

    @Test
    public void CRUD_테스트(){
        Member member1 = new Member("member1");
        Member member2 = new Member("member2");
        memberRepository.save(member1);
        memberRepository.save(member2);

        // 단건 조회
        Member findMember1 = memberRepository.findById(member1.getId()).get();
        Member findMember2 = memberRepository.findById(member2.getId()).get();
        assertThat(findMember1).isEqualTo(member1);
        assertThat(findMember2).isEqualTo(member2);

        // 리스트 조회 검증
        List<Member> all = memberRepository.findAll();
        assertThat(all.size()).isEqualTo(2);

        // 카운트 검증
        long count = memberRepository.count();
        assertThat(count).isEqualTo(2);

        // 삭제 검증
        memberRepository.delete(member1);
        memberRepository.delete(member2);

        long deletedCount = memberRepository.count();
        assertThat(deletedCount).isEqualTo(0);
    }

    @Test
    public void 이름과_나이로_검색(){
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("AAA", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> result = memberRepository.findByUsernameAndAgeGreaterThan("AAA",15);

        assertThat(result.get(0).getUsername()).isEqualTo("AAA");
        assertThat(result.get(0).getAge()).isEqualTo(20);
        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    public void 네임드_쿼리(){
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("AAA", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> result = memberRepository.findByUsername("AAA");

        assertThat(result.get(0).getUsername()).isEqualTo("AAA");
    }

    @Test
    public void JPQL_테스트_findUser(){
        Member m1 = new Member("AAA", 10);
        memberRepository.save(m1);

        List<Member> result = memberRepository.findUser("AAA",10);
        assertThat(result.get(0)).isEqualTo(m1);
    }

    @Test
    public void JPQL_테스트_findUsernameList(){
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("AAA", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<String> result = memberRepository.findUsernameList();
        for (String s : result) {
            System.out.println("s = " + s);
        }
    }

    @Test
    public void JPQL_테스트_DTO로_조회하기(){
        Team team = new Team("teamA");
        teamRepository.save(team);

        Member m1 = new Member("AAA", 10);
        memberRepository.save(m1);

        List<MemberDto> result = memberRepository.findMemberDto();
        for (MemberDto dto : result) {
            System.out.println("dto = " + dto);
        }
    }

    @Test
    public void JPQL_테스트_findByNames(){
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("AAA", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> result = memberRepository.findByNames(Arrays.asList("AAA", "BBB"));
        for (Member member : result) {
            System.out.println("member = " + member);
        }
    }

    @Test
    public void 유연한_반환타입(){
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> list = memberRepository.findListByUsername("AAA");
        Member member = memberRepository.findMemberByUsername("AAA");
        Optional<Member> optional = memberRepository.findOptionByUsername("AAA");
        System.out.println(list);
        System.out.println(member);
        System.out.println(optional);


        // 없는 멤버를 조회하면 빈 컬렉션을 리턴한다.
        List<Member> zeroList = memberRepository.findListByUsername("AAA123");
        System.out.println("result = " + zeroList.size());

        // null 을 린턴한다. NoResultException
        Member nullMember = memberRepository.findMemberByUsername("AAA123");
        System.out.println("result = " + nullMember);

        // 따라서 null 문제를 해결하기 위해 Optional 로 감싸 다음과 같이 처리한다.
        Optional<Member> findMember = memberRepository.findOptionByUsername("AAA");
        findMember.orElseThrow();
    }

    @Test
    public void 페이징_테스트() {
        //given
        IntStream.range(1, 6).forEach(e -> memberRepository.save(new Member("member" + e, 10)));

        int age = 10;
        Pageable pageRequest = PageRequest.of(0, 3, Sort.Direction.DESC, "username");

        //when
        Page<Member> page = memberRepository.findByAge(age, pageRequest);

        //then
        List<Member> content = page.getContent();
        long totalCount = page.getTotalElements();

        for (Member member : content) {
            System.out.println("member = " + member);
        }
        System.out.println("totalElement = " + totalCount);

        assertThat(content.size()).isEqualTo(3);
        assertThat(page.getTotalElements()).isEqualTo(5);
        assertThat(page.getNumber()).isEqualTo(0);
        assertThat(page.getTotalPages()).isEqualTo(2);
        assertThat(page.isFirst()).isTrue();
        assertThat(page.hasNext()).isTrue();
        assertThat(page.isLast()).isFalse();

        Page<MemberDto> toMap = page.map(member -> new MemberDto(member.getId(), member.getUsername(), null));
        for (MemberDto memberDto : toMap) {
            System.out.println("dto => " + memberDto);
        }
    }

    @Test
    public void 벌크_업데이트() throws Exception {
        /**
         * 벌크 업데이트를 할 때 주의할 점!!!
         * 영속성 컨텍스트를 거치지 않고 DB에 때려 부음으로 인해
         * 영속성 컨텍스트와 DB 사이의 괴리가 발생 -> 따라서 벌크 업데이트 후 영속성 컨텍스트를 날려버리자
         * @Modifying(clearAutomatically = true) 를 사용하면 알아서 해줌
        */

        //given
        IntStream.range(1, 6).forEach(e -> memberRepository.save(new Member("member" + e, 17 + e)));

        List<Member> result = memberRepository.findByUsername("member5"); // 22
        Member expect22 = result.get(0);
        System.out.println("member5 = " + expect22);

        //when
        int resultCount = memberRepository.bulkAgePlus(20);
        em.flush(); // 혹시 남아 있는 변경되지 않은 내용을 DB에 반영시키기
        em.clear(); // 영속성 컨텍스트 데이터를 날려버리기

        List<Member> result2 = memberRepository.findByUsername("member5"); // 23
        Member expect23 = result2.get(0);
        System.out.println("member5 = " + expect23);

        //then
        assertThat(resultCount).isEqualTo(3);
        assertThat(expect22.getAge()).isEqualTo(22);
        assertThat(expect23.getAge()).isEqualTo(23);
    }
    
    @Test
    public void Lazy_테스트() throws Exception {
        //given
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        teamRepository.save(teamA);
        teamRepository.save(teamB);

        Member m1 = new Member("m1", 10, teamA);
        Member m2 = new Member("m2", 20, teamB);
        memberRepository.save(m1);
        memberRepository.save(m2);

        em.flush();
        em.clear();

        //when
        List<Member> members = memberRepository.findAll();

        for (Member member : members) {
            System.out.println("member = " + member.getUsername());
            System.out.println("member.team.class = " + member.getTeam().getClass()); // member.team.class = class me.kzv.datajpa.entity.Team$HibernateProxy$iIEMdMSV
            // member 조회 할때는 Team 은 가짜(임시) 객체로 만들어 두고 실제로 필요한 시점에 Team 을 조회해온다.
            // N + 1 문제가 발생!!
            System.out.println("member.team = " + member.getTeam().getName());
        }

        //then
    }

    /**
     * Lazy_테스트 의 N+1 문제 해결하기
     */
    @Test
    public void fetch_join_테스트() throws Exception {

        //given
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        teamRepository.save(teamA);
        teamRepository.save(teamB);

        Member m1 = new Member("m1", 10, teamA);
        Member m2 = new Member("m2", 20, teamB);
        memberRepository.save(m1);
        memberRepository.save(m2);

        em.flush();
        em.clear();

        //when
        List<Member> members = memberRepository.findMemberFetchJoin();

        for (Member member : members) {
            System.out.println("===================================================");
            System.out.println("member = " + member.getUsername());
            System.out.println("member.team.class = " + member.getTeam().getClass());
            System.out.println("member.team = " + member.getTeam().getName());
        }
    }

    /**
     * Lazy_테스트 의 N+1 문제 해결하기
     */
    @Test
    public void fetch_join_엔티티_그래프_사용하기() throws Exception {

        //given
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        teamRepository.save(teamA);
        teamRepository.save(teamB);

        Member m1 = new Member("m1", 10, teamA);
        Member m2 = new Member("m2", 20, teamB);
        memberRepository.save(m1);
        memberRepository.save(m2);

        em.flush();
        em.clear();

        //when
        List<Member> members = memberRepository.findAll();

        for (Member member : members) {
            System.out.println("===================================================");
            System.out.println("member = " + member.getUsername());
            System.out.println("member.team.class = " + member.getTeam().getClass());
            System.out.println("member.team = " + member.getTeam().getName());
        }
    }

    @Test
    public void queryHint(){
        Member member1 = memberRepository.save(new Member("member1", 10));
        Member member2 = memberRepository.save(new Member("member2", 10));
        memberRepository.save(member1);
        memberRepository.save(member2);

        em.flush();
        em.clear();

        Member findMember = memberRepository.findById(member1.getId()).get();
        Member findReadOnlyMember = memberRepository.findReadOnlyByUsername("member2");

        findMember.setUsername("memberA");
        findReadOnlyMember.setUsername("memberB");
        em.flush(); // 변경 감지 후 업데이트 함 -> 원본 데이터까지 2개를 가지고 있어야 함
    }

    @Test
    public void lock_테스트() throws Exception {
        //given
        Member member1 = memberRepository.save(new Member("member1", 10));
        memberRepository.save(member1);

        em.flush();
        em.clear();

        //when
        List<Member> result = memberRepository.findLockByUsername("member1");

        //then
        /*
            select
                member0_.member_id as member_i1_0_,
                member0_.age as age2_0_,
                member0_.team_id as team_id4_0_,
                member0_.username as username3_0_
            from
                member member0_
            where
                member0_.username=? for update
         */
    }

    @Test
    public void 커스텀_레포지토리_테스트(){
        Member member1 = memberRepository.save(new Member("member1", 10));
        memberRepository.save(member1);

        List<Member> memberCustom = memberRepository.findMemberCustom();

        assertThat(memberCustom.get(0)).isEqualTo(member1);
    }

    @Test
    public void 프로젝션_네이티브_쿼리_테스트() throws Exception {
        Member member1 = memberRepository.save(new Member("member1", 10));
        memberRepository.save(member1);


        // 동적 프로젝션
        List<UsernameOnlyDto> result = memberRepository.findProjectionsByUsername("m1", UsernameOnlyDto.class); // type 만 바꾸면 된다

        for (UsernameOnlyDto usernameOnlyDto : result) {
            System.out.println("usernameOnly = " + usernameOnlyDto);
        }

        List<NestedClosedProjections> result2 = memberRepository.findProjectionsByUsername("m1", NestedClosedProjections.class);

        for (NestedClosedProjections nestedClosedProjections : result2) {
            System.out.println("user name = " + nestedClosedProjections.getUsername());
            System.out.println("team name = " + nestedClosedProjections.getTeam().getName());
            // join 이 들어가면 사용하기 애매함.
            // 프로젝션 대상이 root 엔티티를 넘어가면 최적화가 안된다!
        }

        Team teamA = new Team("teamA");
        teamRepository.save(teamA);

        IntStream.range(1, 11).forEach(e -> memberRepository.save(new Member("member" + e, 10, teamA)));

        Page<MemberProjection> result3 = memberRepository.findByNativeProjection(PageRequest.of(1,10));
        List<MemberProjection> content = result3.getContent();
        for (MemberProjection memberProjection : content) {
            System.out.println("user name = " + memberProjection.getUsername());
            System.out.println("team name = " + memberProjection.getTeamName());
        }
    }
}
