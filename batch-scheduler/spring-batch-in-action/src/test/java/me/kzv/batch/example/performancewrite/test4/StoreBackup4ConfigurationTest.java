package me.kzv.batch.example.performancewrite.test4;

import me.kzv.batch.TestBatchConfig;
import me.kzv.batch.entity.product.Product;
import me.kzv.batch.entity.product.ProductRepository;
import me.kzv.batch.entity.product.Store;
import me.kzv.batch.entity.product.StoreRepository;
import me.kzv.batch.entity.product.backup.ProductBackupRepository;
import me.kzv.batch.entity.product.backup.StoreBackup;
import me.kzv.batch.entity.product.backup.StoreBackupRepository;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {TestBatchConfig.class, StoreBackup4Configuration.class})
@SpringBatchTest
public class StoreBackup4ConfigurationTest {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private StoreBackupRepository storeBackupRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductBackupRepository productBackupRepository;

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @AfterEach
    public void after() throws Exception {
        productRepository.deleteAllInBatch();
        productBackupRepository.deleteAllInBatch();

        storeRepository.deleteAllInBatch();
        storeBackupRepository.deleteAllInBatch();
    }

    @Test
    public void H2_Store가_StoreBackup으로_이관된다() throws Exception {
        //given
        String name = "a";

        Store store1 = new Store(name);
        store1.addProduct(new Product(name, 100L, LocalDate.now()));
        store1.addProduct(new Product(name, 100L, LocalDate.now()));
        storeRepository.save(store1);

        JobParameters jobParameters = new JobParametersBuilder(jobLauncherTestUtils.getUniqueJobParameters())
                .addString("storeName", name)
                .toJobParameters();

        //when
        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);

        //then
        assertThat(jobExecution.getStatus()).isEqualTo(BatchStatus.COMPLETED);
        List<StoreBackup> storeBackups = storeBackupRepository.findAll();
        assertThat(storeBackups).hasSize(1);
    }
}
