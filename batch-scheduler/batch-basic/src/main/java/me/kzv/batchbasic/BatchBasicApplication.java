package me.kzv.batchbasic;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class BatchBasicApplication {

    public static void main(String[] args) {
        SpringApplication.run(BatchBasicApplication.class, args);
    }

}
