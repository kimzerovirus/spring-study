package me.kzv.helloquerydsl.repository;

import me.kzv.helloquerydsl.entity.Team;

import java.util.List;

public interface CustomTeamRepository {
    List<Team> findTeamWithMemberByTeamId(Long id);
}
