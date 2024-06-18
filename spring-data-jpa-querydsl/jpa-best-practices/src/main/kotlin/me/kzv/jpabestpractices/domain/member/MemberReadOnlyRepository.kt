package me.kzv.jpabestpractices.domain.member

import org.springframework.data.jpa.repository.JpaRepository

interface MemberReadOnlyRepository : JpaRepository<MemberReadOnly, Long> {
}