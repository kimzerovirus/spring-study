package me.kzv.batch.example.writer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

@Slf4j
public class PrintItemWriter implements ItemWriter<Integer> {

    @Override
    public void write(List<? extends Integer> items) throws Exception {
        log.info("현재 Chunk size= {}", items.size());

        for (Integer item : items) {
            log.info(">>> {}", item);
        }
    }
}
