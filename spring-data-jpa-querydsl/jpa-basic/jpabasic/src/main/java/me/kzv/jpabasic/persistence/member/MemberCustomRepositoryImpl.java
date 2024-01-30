package me.kzv.jpabasic.persistence.member;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import me.kzv.jpabasic.persistence.member.dto.MemberSearchDto;
import me.kzv.jpabasic.persistence.member.dto.MemberWithTeamDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigInteger;
import java.util.List;

@RequiredArgsConstructor
public class MemberCustomRepositoryImpl implements MemberCustomRepository {

    @PersistenceContext
    private EntityManager em;

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
        if (!searchByMemberName.isBlank()) {
            objectQBuilder.append(" and m.member_name like '%' || :memberName || '%' ");
            countQBuilder.append(" and m.member_name like '%' || :memberName || '%' ");
        }

        if (!searchByTeamName.isBlank()) {
            objectQBuilder.append(" and t.team_name like '%' || :teamName || '%' ");
            countQBuilder.append(" and t.team_name like '%' || :teamName || '%' ");
        }

        Query objectQ = em.createNativeQuery(objectQBuilder.toString());
        Query countQ = em.createNativeQuery(countQBuilder.toString());

        // parameter setting
        if (!searchByMemberName.isBlank()) {
            objectQ.setParameter("memberName", searchByMemberName);
            countQ.setParameter("memberName", searchByMemberName);
        }

        if (!searchByTeamName.isBlank()) {
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
}
