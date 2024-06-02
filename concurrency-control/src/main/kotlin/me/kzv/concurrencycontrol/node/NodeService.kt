package me.kzv.concurrencycontrol.node

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class NodeService(
    private val nodeRepository: NodeRepository,
) {
    @Transactional
    fun createWithNoneLock(node: Node): Node {
        val savedNode = nodeRepository.save(node)
        with(savedNode) {
            savedNode.updateName("${nodeType}_$id")
        }

        return savedNode
    }

    fun createWithOptimisticLock() {

    }
}