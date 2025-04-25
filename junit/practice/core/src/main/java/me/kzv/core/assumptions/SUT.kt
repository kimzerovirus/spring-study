package me.kzv.core.assumptions

class SUT (
    var currentJob: Job? = null,
){
    fun run(currentJob: Job?) {
        this.currentJob = currentJob
    }

    fun hasJobToRun(): Boolean {
        return currentJob != null
    }
}