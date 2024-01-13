package me.kzv.springevents.defaultevent;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CustomEventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    // 커스텀 이벤트를 발행할 수 있는 게시자를 생성, publish 메서드에서 ApplicationEventPublisher 로 이벤트를 발행
    public void publish(final String message) {
        System.out.println("Publishing custom event. ");
        CustomEvent customSpringEvent = new CustomEvent(this, message);
        applicationEventPublisher.publishEvent(customSpringEvent);
    }
}
