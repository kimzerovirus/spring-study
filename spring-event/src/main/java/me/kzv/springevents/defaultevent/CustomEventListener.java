package me.kzv.springevents.defaultevent;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class CustomEventListener implements ApplicationListener<CustomEvent> {

    // ApplicationEventPublisher 에 발행된 이벤트는 ApplicationListener 를 확장하여 구현할 수 있음.
    @Override
    public void onApplicationEvent(CustomEvent event) {
        try {
            Thread.sleep(3000); // 비동기 테스트를 위한 의도적인 이벤트 지연
            System.out.println("Received spring custom event - " + event.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
