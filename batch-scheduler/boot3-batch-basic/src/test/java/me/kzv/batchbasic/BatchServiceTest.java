package me.kzv.batchbasic;

import me.kzv.batchbasic.config.JobConstants;
import me.kzv.batchbasic.service.JobService;
import org.junit.jupiter.api.Test;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBatchTest
@SpringBootTest
class BatchServiceTest {

    @Autowired JobService jobService;

    @Test
    public void basicJobTest() throws Exception {
        jobService.invokeJob(JobConstants.BASIC_JOB_NAME);
    }
}