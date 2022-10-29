package me.kzv.shop.repository

import org.springframework.data.jpa.repository.JpaRepository
import java.lang.reflect.Member

interface MemberRepository : JpaRepository<Member, Long> {
}