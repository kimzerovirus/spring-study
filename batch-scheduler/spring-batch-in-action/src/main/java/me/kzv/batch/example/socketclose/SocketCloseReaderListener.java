package me.kzv.batch.example.socketclose;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemReadListener;

@Slf4j
public class SocketCloseReaderListener<T> implements ItemReadListener<T> {

    @Override
    public void beforeRead() {
        log.info("beforeRead start");
        try {
            Thread.sleep(70_000);// 2.5% 버퍼 대비 넉넉하게 70초로
        } catch (InterruptedException e) {
            throw new IllegalStateException("Listener Exception");
        }
        log.info("beforeRead end");
    }

    @Override
    public void afterRead(Object item) {

    }

    @Override
    public void onReadError(Exception ex) {

    }
}
