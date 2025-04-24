package me.kzv.core.assertions

class SUT(
    val systemName: String,
    var isVerified: Boolean = false
) {
    private val jobs: MutableList<Job> = ArrayList()
    private var currentJob: Job? = null

    fun verify() {
        this.isVerified = true
    }

    fun addJob(job: Job) {
        jobs.add(job)
    }

    fun run() {
        if (jobs.size > 0) {
            currentJob = jobs.removeAt(0)
            println("Running job: $currentJob")
            return
        }
        throw NoJobException("실행할 작업이 없습니다!")
    }

    @Throws(InterruptedException::class)
    fun run(jobDuration: Int) {
        if (jobs.size > 0) {
            currentJob = jobs.removeAt(0)
            println("Running job: $currentJob lasts $jobDuration milliseconds")
            Thread.sleep(jobDuration.toLong())
            return
        }
        throw NoJobException("실행할 작업이 없습니다!")
    }
}
