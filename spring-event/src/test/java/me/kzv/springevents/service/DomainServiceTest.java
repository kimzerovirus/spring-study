package me.kzv.springevents.service;

import me.kzv.springevents.defaultevent.CustomEvent;
import me.kzv.springevents.defaultevent.CustomEventListener;
import me.kzv.springevents.domain.User;
import me.kzv.springevents.domain.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

@SpringBootTest
class DomainServiceTest {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private DomainService domainService;

    @MockBean
    private CustomEventListener customEventListener;

    @Test
    void serviceEventTest() {
        // 유저 정보 저장 -> 서비스 레이어에서 유저의 나이를 한 살 증가 + 이벤트 발행 -> CustomEventListener 가 1회 동작하는지 확인
        User user = userRepo.save(User.builder().userId("kzv").age(30).created(LocalDateTime.now()).build());

        // when
        domainService.addAge(user.getUserId());

        // then
        Mockito.verify(customEventListener, times(1)).onApplicationEvent(any(CustomEvent.class));
    }
}