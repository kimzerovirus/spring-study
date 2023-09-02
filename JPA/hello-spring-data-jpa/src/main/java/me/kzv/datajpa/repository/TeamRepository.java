package me.kzv.datajpa.repository;

import me.kzv.datajpa.entity.Team;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {

    @EntityGraph(attributePaths = {"members"}, type = EntityGraph.EntityGraphType.LOAD)
    Optional<Team> findByName(String name);

    @Override // 기본적으로 제공하는 메서드이므로 오버라이드해서 구현한다
    @EntityGraph(attributePaths = {"members"})
    List<Team> findAll();
}
