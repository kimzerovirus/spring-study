package me.kzv.batchbasic.batch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 1. @Configuration
 *  - Spring Batch의 모든 Job은 @Configuration으로 등록해서 사용합니다.
 *
 * 2. jobBuilderFactory.get("simpleJob")
 *  - simpleJob 이란 이름의 Batch Job을 생성합니다.
 *  - job의 이름은 별도로 지정하지 않고, 이렇게 Builder를 통해 지정합니다.
 *
 * 3. stepBuilderFactory.get("simpleStep1")
 *  - simpleStep1 이란 이름의 Batch Step을 생성합니다.
 *  - jobBuilderFactory.get("simpleJob")와 마찬가지로 Builder를 통해 이름을 지정합니다.
 *
 * 4. tasklet((contribution, chunkContext))
 *  - Step 안에서 수행될 기능들을 명시합니다.
 *  - Tasklet은 Step안에서 단일로 수행될 커스텀한 기능들을 선언할때 사용합니다.
 *  - 여기서는 Batch가 수행되면 log.info(">>>>> This is Step1") 가 출력되도록 합니다.
 *
 *
 * 동일한 Job이 Job Parameter가 달라지면 그때마다 BATCH_JOB_INSTANCE 테이블에 생성되며, 동일한 Job Parameter는 여러개 존재할 수 없다 .
 *
 * JOB_EXECUTION와 JOB_INSTANCE는 부모-자식 관계입니다.
 * JOB_EXECUTION은 자신의 부모 JOB_INSTACNE가 성공/실패했던 모든 내역을 갖고 있습니다.
 */

@Slf4j // log 사용을 위한 lombok 어노테이션
@RequiredArgsConstructor // 생성자 DI를 위한 lombok 어노테이션
@Configuration
public class SimpleJobConfiguration {
    private final JobBuilderFactory jobBuilderFactory; // 생성자 DI 받음
    private final StepBuilderFactory stepBuilderFactory; // 생성자 DI 받음

    @Bean
    public Job simpleJob() {
        return jobBuilderFactory.get("simpleJob")
                .start(simpleStep1())
                .build();
    }

    @Bean
    public Step simpleStep1() {
        return stepBuilderFactory.get("simpleStep1")
                .tasklet((contribution, chunkContext) -> {
                    log.info(">>>>> This is Step1");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
