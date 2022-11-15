package me.kzv.simpleboard.web.repository;

import me.kzv.simpleboard.web.domain.Board;
import me.kzv.simpleboard.web.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

    Board findByMember(Member member);
}
