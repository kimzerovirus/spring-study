package me.kzv.core.assumptions.environment

class KotlinSpecification {
    private var version: String? = null

    fun kotlinSpecification(version: String?) {
        this.version = version
    }

    fun getVersion(): String? {
        return version
    }
}