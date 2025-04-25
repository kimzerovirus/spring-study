package me.kzv.core.lifecycle

class ResourceForAllTests(private val resourceName: String) {
    init {
        println(resourceName + " from class " + javaClass.simpleName + " is initializing.")
    }

    fun close() {
        println(resourceName + " from class " + javaClass.simpleName + " is closing.")
    }
}
