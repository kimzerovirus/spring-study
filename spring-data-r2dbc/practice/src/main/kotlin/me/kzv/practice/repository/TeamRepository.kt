package me.kzv.practice.repository

import me.kzv.practice.entity.Team
import org.springframework.data.r2dbc.repository.R2dbcRepository

interface TeamRepository : R2dbcRepository<Team, Long> {

}