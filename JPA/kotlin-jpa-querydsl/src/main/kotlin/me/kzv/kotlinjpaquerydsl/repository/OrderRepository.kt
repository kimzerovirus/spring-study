package me.kzv.kotlinjpaquerydsl.repository

import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<OrderRepository, Long> {

}