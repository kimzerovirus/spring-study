package me.kzv.springevents.service;

import lombok.RequiredArgsConstructor;
import me.kzv.springevents.defaultevent.CustomEventPublisher;
import me.kzv.springevents.domain.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *  서비스에서 수동으로 Event 게시하기
 *
 *  도메인 이벤트를 게시할 수 있는 부분은 2가지가 존재하는데
 *  첫번째 서비스 레이어에 게시하는 것이고
 *  두번재는 Aggregate 안에 게시하는 것이다.
 *
 *  아래와 같이 서비스 레이어에서 repository 에 save 를 진행하고 간단하게 이벤트를 게시할 수 있다.
 */
@RequiredArgsConstructor
@Service
public class DomainService {

    private final UserRepository userRepository;
    private final CustomEventPublisher customEventPublisher;

    @Transactional
    public void addAge(String userId) {
        userRepository.findByUserId(userId)
                .ifPresent(entity -> {
                    entity.addAge();
                    userRepository.save(entity);
//                    customEventPublisher.publish("user age is "+entity.getAge()); <- 도메인에서 설정하였으므로 서비스에서 확인 안해도됨
                });
    }
}
