package me.kzv.springevents.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import me.kzv.springevents.defaultevent.CustomEvent;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Getter
@Builder
@AllArgsConstructor
//@Entity
//@Table(name="users")
public class UserOld {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userNo;
    private String userId;
    private int age;
    private LocalDateTime created;

    @Transient
    private final Collection<CustomEvent> customEvents;

    public UserOld(){
        this.customEvents = new ArrayList<>();
    }

    @DomainEvents
    public Collection<CustomEvent> events(){
        return this.customEvents;
    }

    @AfterDomainEventPublication
    public void clearEvents(){
        this.customEvents.clear(); // 사용된 이벤트는 삭제하여 중복 발행을 방지
    }

    // domain operation
    public void addAge() {
        this.age++;
        customEvents.add(new CustomEvent(this, "user Age is " + this.age));
    }
}

/**
 * user aggregate 의 경우 plain java 이므로 applicationEventPublisher 를 주입받기가 어렵다
 * 이를 위해 스프링에서는 @DomainEvent 어노테이션을 지원한다.
 *
 * 간단히 설명하면 도메인 이벤트 발행을 위한 Custom Event 목록을 만들고 이벤트를 하나 발행하면 @DomainEvent 어노테이션에 의해 해당 Event 목록이 ApplicationEventPublisher 에 발행되는 것이다.
 *
 * 도메인 이벤트가 발행된 후 @AfterDomainEventsPublication 어노테이션이 달린 메서드가 호출된다.
 * 모든 이벤트 목록을 지움으로써 향후 중복으로 이벤트가 발행되는 것을 막는다.
 */
