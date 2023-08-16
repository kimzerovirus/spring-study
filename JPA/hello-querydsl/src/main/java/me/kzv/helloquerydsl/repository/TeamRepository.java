package me.kzv.helloquerydsl.repository;

import me.kzv.helloquerydsl.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long>, CustomTeamRepository {
}
