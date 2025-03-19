package me.kzv.practice.config

import org.h2.tools.Server
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.ContextClosedEvent
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener

@Configuration
class H2Config {
    private var webServer: Server? = null
    private var tcpServer: Server? = null

    @EventListener
    fun start(event: ContextRefreshedEvent?) {
        this.webServer = Server.createWebServer("-webPort", "8090", "-tcpAllowOthers").start()
        this.tcpServer = Server.createTcpServer("-tcpPort", "9092", "-tcpAllowOthers").start()
    }

    @EventListener
    fun stop(event: ContextClosedEvent?) {
        this.webServer?.stop()
        this.tcpServer?.stop()
    }
}