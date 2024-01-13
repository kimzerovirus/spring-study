package me.kzv.springevents.controller;

import lombok.RequiredArgsConstructor;
import me.kzv.springevents.defaultevent.CustomEventPublisher;
import me.kzv.springevents.genericevent.GenericEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class EventController {
    private final CustomEventPublisher customEventPublisher;
    private final GenericEventPublisher genericEventPublisher;

    @GetMapping("/event")
    public String event(@RequestParam String message) {
        customEventPublisher.publish(message);
        return "finished.";
    }

    @GetMapping("/event/generic")
    public String event(@RequestParam String message, @RequestParam boolean success) {
        genericEventPublisher.publish(message, success);
        return "finished";
    }
}

/**
 * Transaction 바운더리에서의 이벤트 처리
 *
 * @EventListener의 확장인 @TransactionalEventListener 를 사용하면 트랜잭션 상태에 따른 이벤트 처리가 가능함.
 * phase 를 설정하지 않으면 트랜잭션이 성공적으로 완료되었을 때 이벤트가 실행됨
 *
 * AFTER_COMMIT (default) – 트랜잭션이 성공했을때 실행
 * AFTER_ROLLBACK – 트랜잭션이 롤백되었을때 실행
 * AFTER_COMPLETION – 트랜잭션이 완료되었을때 실행 (AFTER_COMMIT 및 AFTER_ROLLBACK 이 완료되었을때)
 * BEFORE_COMMIT – 트랜잭션이 commit 되기 전에 실행
 */
