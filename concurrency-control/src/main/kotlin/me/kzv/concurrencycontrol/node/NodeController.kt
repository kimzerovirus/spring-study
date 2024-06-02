package me.kzv.concurrencycontrol.node

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class NodeController(
    private val nodeService: NodeService
) {

    @GetMapping("none-lock")
    fun noneLock(@RequestBody node: Node): Node {
        return nodeService.createWithNoneLock(node)
    }

    @GetMapping("optimistic-lock")
    fun optimisticLock(@RequestBody node: Node): Node {
        return nodeService.createWithNoneLock(node)
    }
}