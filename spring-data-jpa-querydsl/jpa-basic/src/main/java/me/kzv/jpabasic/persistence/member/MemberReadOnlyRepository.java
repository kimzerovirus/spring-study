package me.kzv.jpabasic.persistence.member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberReadOnlyRepository extends JpaRepository<MemberReadOnly, Long> {
}
