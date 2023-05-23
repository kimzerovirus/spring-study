package me.kzv.springevents.defaultevent;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AnnotationListener {
    @Async // 어노 테이션으로 비동기 처리
    @EventListener
    public void handleEvent(CustomEvent event) {
        try {
            Thread.sleep(3000);
            System.out.println("Received spring custom event by annotation listener - " + event.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/**
 * 리스너 구현을 위해 일일이 ApplicationListener 를 구현할 필요 없이
 * @EventListener 어노테이션을 통해 관리 빈의 모든 공용 메서드에 리스너를 등록할 수 있음.
 * 따라서 위에서 작성한 CustomEventListener 는 다음과 같이 대체할 수 있다.
 */