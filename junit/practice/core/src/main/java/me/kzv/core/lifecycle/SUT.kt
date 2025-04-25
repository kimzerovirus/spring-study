package me.kzv.core.lifecycle

class SUT(private val systemName: String) {
    init {
        println(systemName + " from class " + javaClass.simpleName + " is initializing.")
    }

    fun canReceiveRegularWork(): Boolean {
        println(systemName + " from class " + javaClass.simpleName + " can receive regular work.")
        return true
    }

    fun canReceiveAdditionalWork(): Boolean {
        println(systemName + " from class " + javaClass.simpleName + " cannot receive additional work.")
        return false
    }

    fun close() {
        println(systemName + " from class " + javaClass.simpleName + " is closing.")
    }
}