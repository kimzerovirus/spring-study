package me.kzv.jpabasic.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberReadOnlyRepository extends JpaRepository<MemberReadOnly, Long> {
}
