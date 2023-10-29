package me.kzv.batchbasic.config;

import me.kzv.batchbasic.service.JobService;
import org.junit.jupiter.api.Test;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBatchTest
@SpringBootTest
class BatchConfigTest {

    @Autowired JobService jobService;

    @Test
    public void jobTest() throws Exception {
        jobService.invokeJob(JobConstants.JOB_NAME);
    }
}