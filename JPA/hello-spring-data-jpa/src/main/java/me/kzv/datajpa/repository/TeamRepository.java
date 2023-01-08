package me.kzv.datajpa.repository;

import me.kzv.datajpa.entity.Team;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {

    @EntityGraph(attributePaths = {"members"}, type = EntityGraph.EntityGraphType.LOAD)
    Optional<Team> findByName(String name);
}
