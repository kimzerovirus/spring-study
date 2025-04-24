package me.kzv.core.assertions

import java.util.*

class Job(
    var name: String,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val job = other as Job
        return name == job.name
    }

    override fun hashCode(): Int {
        return Objects.hash(name)
    }

    override fun toString(): String {
        return "Job{" +
                "getName='" + name + '\'' +
                '}'
    }
}