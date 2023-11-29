package me.kzv.batch.example.socketclose;

import me.kzv.batch.TestBatchConfig;
import me.kzv.batch.entity.product.Store;
import me.kzv.batch.entity.product.StoreRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {TestBatchConfig.class, SocketCloseSlowNoTxBatch.class})
@SpringBatchTest
@ActiveProfiles(profiles = "real")
public class RealSocketCloseSlowNoTxBatchTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private StoreRepository storeRepository;

    @AfterEach
    void after() {
        storeRepository.deleteAll();
    }

    @Test
    public void ResourcelessTransactionManager를_사용하면_실패하지않는다() throws Exception {
        //given
        storeRepository.save(new Store("jojoldu"));

        JobParameters jobParameters = new JobParametersBuilder(jobLauncherTestUtils.getUniqueJobParameters())
                .toJobParameters();

        //when
        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);

        //then
        assertThat(jobExecution.getStatus()).isEqualTo(BatchStatus.COMPLETED);
    }
}