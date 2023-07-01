package me.kzv.jdbcjpas3.test;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestMemberRepository extends JpaRepository<TestMember, Long> {
    List<TestMember> findAll();
}
