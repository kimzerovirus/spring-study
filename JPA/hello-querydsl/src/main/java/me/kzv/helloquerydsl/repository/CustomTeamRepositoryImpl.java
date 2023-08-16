package me.kzv.helloquerydsl.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import me.kzv.helloquerydsl.entity.QMember;
import me.kzv.helloquerydsl.entity.Team;

import java.util.List;

import static me.kzv.helloquerydsl.entity.QMember.*;
import static me.kzv.helloquerydsl.entity.QTeam.team;

@RequiredArgsConstructor
public class CustomTeamRepositoryImpl implements CustomTeamRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Team> findTeamWithMemberByTeamId(Long id) {
        return queryFactory
                .selectFrom(team)
                .leftJoin(team.members, member)
                .where(team.id.eq(id)).fetch();
    }
}
