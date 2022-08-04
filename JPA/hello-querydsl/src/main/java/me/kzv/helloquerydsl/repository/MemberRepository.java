package me.kzv.helloquerydsl.repository;

import me.kzv.helloquerydsl.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long>, CustomMemberRepository {
    List<Member> findByUsername(String username);
}
