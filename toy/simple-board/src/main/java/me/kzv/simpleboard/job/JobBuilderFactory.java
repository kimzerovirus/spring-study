package me.kzv.simpleboard.job;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;

@RequiredArgsConstructor
public class JobBuilderFactory {
    private JobRepository jobRepository;

    public JobBuilder get(String name) {
        JobBuilder builder = new JobBuilder(name).repository(jobRepository);
        return builder;
    }

}
