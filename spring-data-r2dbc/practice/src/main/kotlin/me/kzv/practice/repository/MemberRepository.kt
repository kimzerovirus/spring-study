package me.kzv.practice.repository

import me.kzv.practice.entity.Member
import org.springframework.data.r2dbc.repository.R2dbcRepository

interface MemberRepository : R2dbcRepository<Member, Long> {

}