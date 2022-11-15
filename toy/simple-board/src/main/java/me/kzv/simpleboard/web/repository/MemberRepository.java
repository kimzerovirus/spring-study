package me.kzv.simpleboard.web.repository;

import me.kzv.simpleboard.web.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByEmail(String email);
}
