package me.kzv.batchbasic.dbbatch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.kzv.batchbasic.entity.Pay;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;

/**
 * reader는 Tasklet이 아니기 때문에 reader만으로는 수행될수 없고, 간단한 출력 Writer를 하나 추가했습니다.
 *
 * processor는 필수가 아닙니다.
 * 위 예제처럼 reader에서 읽은 데이터에 대해 크게 변경 로직이 없다면 processor를 제외하고 writer만 구현하시면 됩니다.
 *
 * JdbcCursorItemReader의 설정값들은 다음과 같은 역할을 합니다
 *
 * 1. chunk
 *  - <Pay, Pay> 에서 첫번째 Pay는 Reader에서 반환할 타입이며, 두번째 Pay는 Writer에 파라미터로 넘어올 타입을 얘기합니다.
 *  - chunkSize로 인자값을 넣은 경우는 Reader & Writer가 묶일 Chunk 트랜잭션 범위입니다.
 *
 * 2. fetchSize
 *  - Database에서 한번에 가져올 데이터 양을 나타냅니다.
 *  - Paging과는 다른 것이, Paging은 실제 쿼리를 limit, offset을 이용해서 분할 처리하는 반면, Cursor는 쿼리는 분할 처리 없이 실행되나 내부적으로 가져오는 데이터는 FetchSize만큼 가져와 read()를 통해서 하나씩 가져옵니다.
 *
 * 3. dataSource
 *  - Database에 접근하기 위해 사용할 Datasource 객체를 할당합니다
 *
 * 4. rowMapper
 *  - 쿼리 결과를 Java 인스턴스로 매핑하기 위한 Mapper 입니다.
 *  - 커스텀하게 생성해서 사용할 수 도 있지만, 이렇게 될 경우 매번 Mapper 클래스를 생성해야 되서 보편적으로는 Spring에서 공식적으로 지원하는 BeanPropertyRowMapper.class를 많이 사용합니다
 *
 * 5. sql
 *  - Reader로 사용할 쿼리문을 사용하시면 됩니다.
 *
 * 6. name
 *  - reader의 이름을 지정합니다.
 *  - Bean의 이름이 아니며 Spring Batch의 ExecutionContext에서 저장되어질 이름입니다.
 *
 *
 * JdbcItemReader는 JdbcTemplate 과 인터페이스가 동일하다.
 */

@Slf4j
@RequiredArgsConstructor
@Configuration
public class JdbcCursorItemReaderJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final DataSource dataSource; // DataSource DI

    private static final int chunkSize = 10;

    @Bean
    public Job jdbcCursorItemReaderJob() {
        return jobBuilderFactory.get("jdbcCursorItemReaderJob")
                .start(jdbcCursorItemReaderStep())
                .build();
    }

    @Bean
    public Step jdbcCursorItemReaderStep() {
        return stepBuilderFactory.get("jdbcCursorItemReaderStep")
                .<Pay, Pay>chunk(chunkSize)
                .reader(jdbcCursorItemReader())
                .writer(jdbcCursorItemWriter())
                .build();
    }

    @Bean
    public JdbcCursorItemReader<Pay> jdbcCursorItemReader() {
        return new JdbcCursorItemReaderBuilder<Pay>()
                .fetchSize(chunkSize)
                .dataSource(dataSource)
                .rowMapper(new BeanPropertyRowMapper<>(Pay.class))
                .sql("SELECT id, amount, tx_name, tx_date_time FROM pay")
                .name("jdbcCursorItemReader")
                .build();
    }

    private ItemWriter<Pay> jdbcCursorItemWriter() {
        return list -> {
            for (Pay pay: list) {
                log.info("Current Pay={}", pay);
            }
        };
    }
}