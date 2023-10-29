package me.kzv.batch.example.multithread;

import me.kzv.batch.entity.product.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemReadListener;

@Slf4j
public class CursorItemReaderListener implements ItemReadListener<Product> {

    @Override
    public void beforeRead() {

    }

    @Override
    public void afterRead(Product item) {
        log.info("Reading Item id={}", item.getId());
    }

    @Override
    public void onReadError(Exception ex) {

    }
}
