package me.kzv.jpabasic.domain.member;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import me.kzv.jpabasic.domain.member.dto.MemberSearchDto;
import me.kzv.jpabasic.domain.member.dto.MemberWithTeamDto;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class MemberCustomRepositoryImpl implements MemberCustomRepository {

    @PersistenceContext // @Autowired가 스프링 빈을 주입한다면, @PersistenceContext는 JPA 스펙에서 제공하는 기능인데, 영속성 컨텍스트를 주입하는 표준 애노테이션
    private EntityManager em;

    private final NamedParameterJdbcTemplate jdbcTemplate; // 기존의 ?에 매핑되던 jdbcTemplate 과 달리 변수명과 매핑 시킬 수 있다.

    @Override
    public Page<MemberWithTeamDto> searchMemberList(MemberSearchDto memberSearchDto) {
        // object query start
        StringBuilder objectQBuilder = new StringBuilder();
        objectQBuilder.append("select m.id, m.member_name, t.id, t.team_name");
        objectQBuilder.append(" from member m");
        objectQBuilder.append(" left join team t on m.team_id = m.id");
        objectQBuilder.append(" where 1 = 1");

        // count query start
        StringBuilder countQBuilder = new StringBuilder();
        countQBuilder.append("select");
        countQBuilder.append(" count(m)");
        countQBuilder.append(" from tbl_member m");
        countQBuilder.append(" where 1 = 1");

        String searchByMemberName = memberSearchDto.getMemberName();
        String searchByTeamName = memberSearchDto.getTeamName();

        // where query start
        if (ObjectUtils.isNotEmpty(searchByMemberName)) {
            objectQBuilder.append(" and m.member_name like '%' || :memberName || '%' ");
            countQBuilder.append(" and m.member_name like '%' || :memberName || '%' ");
        }

        if (ObjectUtils.isNotEmpty(searchByTeamName)) {
            objectQBuilder.append(" and t.team_name like '%' || :teamName || '%' ");
            countQBuilder.append(" and t.team_name like '%' || :teamName || '%' ");
        }

        Query objectQ = em.createNativeQuery(objectQBuilder.toString());
        Query countQ = em.createNativeQuery(countQBuilder.toString());

        // parameter setting
        if (ObjectUtils.isNotEmpty(searchByMemberName)) {
            objectQ.setParameter("memberName", searchByMemberName);
            countQ.setParameter("memberName", searchByMemberName);
        }

        if (ObjectUtils.isNotEmpty(searchByTeamName)) {
            objectQ.setParameter("teamName", searchByTeamName);
            countQ.setParameter("teamName", searchByTeamName);
        }

        Pageable pageable = PageRequest.of(memberSearchDto.getPage(), memberSearchDto.getSize());
        Number totalCount = (Number) countQ.getSingleResult();

        objectQ.setFirstResult((int) pageable.getOffset());
        if (memberSearchDto.getSize() > 0) {
            objectQ.setMaxResults(Math.min(memberSearchDto.getSize(), totalCount.intValue()));
        }

        List<MemberWithTeamDto> content = ((List<Object[]>) objectQ.getResultList()).stream()
                .map(row -> MemberWithTeamDto.builder()
                        .memberId(bigIntegerToLong(row[0]))
                        .memberName((String) row[1])
                        .teamId(bigIntegerToLong(row[2]))
                        .teamName((String) row[3])
                        .build()).toList();

        return new PageImpl<>(content, pageable, totalCount.longValue());
    }

    private Long bigIntegerToLong(Object bi) {
        return bi != null ? ((BigInteger) bi).longValue() : null;
    }

    // 위에 작성한 native query 를 jdbc template 으로 비교해보기
    @Override
    public Page<MemberWithTeamDto> searchMemberListWithJdbcTemplate(MemberSearchDto memberSearchDto) {
        // object query start
        StringBuilder objectQBuilder = new StringBuilder();
        objectQBuilder.append("select m.id as member_id, m.member_name, t.id as team_id, t.team_name");
        objectQBuilder.append(" from member m");
        objectQBuilder.append(" left join team t on m.team_id = m.id");
        objectQBuilder.append(" where 1 = 1");

        // count query start
        StringBuilder countQBuilder = new StringBuilder();
        countQBuilder.append("select");
        countQBuilder.append(" count(m)");
        countQBuilder.append(" from tbl_member m");
        countQBuilder.append(" where 1 = 1");

        String searchByMemberName = memberSearchDto.getMemberName();
        String searchByTeamName = memberSearchDto.getTeamName();

        MapSqlParameterSource paramByMapSqlParameterSource = new MapSqlParameterSource();

        // where query start
        if (ObjectUtils.isNotEmpty(searchByMemberName)) {
            objectQBuilder.append(" and m.member_name like '%' || :memberName || '%' ");
            countQBuilder.append(" and m.member_name like '%' || :memberName || '%' ");
            paramByMapSqlParameterSource.addValue("memberName", searchByMemberName);
        }

        if (ObjectUtils.isNotEmpty(searchByTeamName)) {
            objectQBuilder.append(" and t.team_name like '%' || :teamName || '%' ");
            countQBuilder.append(" and t.team_name like '%' || :teamName || '%' ");
            paramByMapSqlParameterSource.addValue("teamName", searchByTeamName);
        }

        // 여러가지 방법으로 파라미터를 세팅할 수 있다.
        Map<String, Object> paramByMap = Map.of("memberName", searchByMemberName, "teamName", searchByTeamName);

        SqlParameterSource paramByBeanPropertySqlParameterSource = new BeanPropertySqlParameterSource(memberSearchDto);

        Pageable pageable = PageRequest.of(memberSearchDto.getPage(), memberSearchDto.getSize());

        Integer totalCount = jdbcTemplate.queryForObject(countQBuilder.toString(), paramByMapSqlParameterSource, Integer.class);
        List<MemberWithTeamDto> content = jdbcTemplate.query(objectQBuilder.toString(), paramByMapSqlParameterSource, itemRowMapper2());

        return new PageImpl<>(content, pageable, totalCount);
    }

    private RowMapper<MemberWithTeamDto> itemRowMapper() {
        return BeanPropertyRowMapper.newInstance(MemberWithTeamDto.class);   // camel 변환 지원
    }

    public RowMapper<MemberWithTeamDto> itemRowMapper2() {
        return (rs, rowNum) -> MemberWithTeamDto.builder()
                .memberId(rs.getLong("member_id"))
                .memberName(rs.getString("member_name"))
                .teamId(rs.getLong("team_id"))
                .teamName(rs.getString("team_name"))
                .build();
    }

}
