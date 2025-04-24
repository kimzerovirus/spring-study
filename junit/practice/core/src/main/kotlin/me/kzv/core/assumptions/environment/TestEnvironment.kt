package me.kzv.core.assumptions.environment

class TestsEnvironment(
    private val javaSpecification: JavaSpecification,
    private val operationSystem: OperationSystem
) {
    val isWindows: Boolean
        get() = operationSystem.name.contains("Windows")

    val isAmd64Architecture: Boolean
        get() = operationSystem.architecture == "amd64"

    val javaVersion: String
        get() = javaSpecification.version
}
