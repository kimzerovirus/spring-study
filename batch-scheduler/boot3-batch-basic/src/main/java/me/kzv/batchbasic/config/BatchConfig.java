package me.kzv.batchbasic.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

/**
 * @EnableBatchProcessing 어노테이션 사용과 DefaultBatchConfigurer 상속은 함께 사용하면 안된다.
 * @EnableBatchProcessing Annotation이 4.x버전까지 modular만 제공하는 것에 다양한 옵션을 제공한다.
 * spring batch5++ 부터는 DefaultBatchConfiguration 상속하여 간편하게 설정하거나 직접 설정하는듯?
 *
 * @EnableBatchProcessing가 JobRepository, JobLauncher, StepScope, JobScope 등의 Bean을 등록하고 마법을 부리는 것을 대신할 Configuration class가 추가되었다.
 *
 * 아래처럼 DefaultBatchConfigurer를  상속하여 사용하면 된다.
 *
 * what's new in spring batch 5.0
 * https://docs.spring.io/spring-batch/docs/current/reference/html/whatsnew.html
 */

@Slf4j
@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchConfig {
    private final PlatformTransactionManager batchTransactionManager;
    private final JobRepository jobRepository;

    public static final Logger logger = LoggerFactory.getLogger(BatchConfig.class);


    /**
     * Job which contains multiple steps
     */
    @Bean
    public Job basicJob() {
        return new JobBuilder(JobConstants.BASIC_JOB_NAME)
                .repository(jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(chunkStep())
                .next(taskletStep())
                .build();
    }

    @Bean
    public Step taskletStep () {
        return new StepBuilder(JobConstants.BASIC_JOB_NAME)
                .repository(jobRepository)
                .transactionManager(batchTransactionManager)
                .tasklet((stepContribution, chunkContext) -> {
                    logger.info("This is first tasklet step");
                    logger.info("SEC = {}", chunkContext.getStepContext().getStepExecutionContext());
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Step chunkStep() {
        return new StepBuilder(JobConstants.BASIC_JOB_NAME)
                .repository(jobRepository)
                .transactionManager(batchTransactionManager)
                .<String, String>chunk(JobConstants.BATCH_SIZE)
                .reader(reader())
                .writer(writer())
                .build();
    }

    @Bean
    public ItemReader<String> reader() {
        List<String> data = List.of("Byte", "Code", "Data", "Disk", "File", "Input", "Loop", "Logic", "Mode", "Node");
        return new ListItemReader<>(data);
    }

    @Bean
    public ItemWriter<String> writer() {
        return items -> {
            for (String item : items) {
                logger.info("Writing item: {}", item);
            }
            logger.info("------------ BATCH_SIZE: {}, documents written. ------------", JobConstants.BATCH_SIZE);
        };
    }


}
