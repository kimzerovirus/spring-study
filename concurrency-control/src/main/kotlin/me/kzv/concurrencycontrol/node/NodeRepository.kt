package me.kzv.concurrencycontrol.node

import jakarta.persistence.LockModeType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query


interface NodeRepository : JpaRepository<Node, Long> {

    @Lock(value = LockModeType.OPTIMISTIC)
    @Query("select n from Node n where n.id = :id")
    fun findByIdWithOptimisticLock(id: Long?): Node?
}