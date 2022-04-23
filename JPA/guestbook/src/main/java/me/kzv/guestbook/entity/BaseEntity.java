package me.kzv.guestbook.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass // 해당 클래스는 실제 테이블로는 생성 되지 않는다.
@EntityListeners(value = {AuditingEntityListener.class})
@Getter
abstract class BaseEntity {

    @CreatedDate
    @Column(name = "regdate", updatable = false)
    private LocalDateTime regDate;

    @LastModifiedDate
    @Column(name = "moddate")
    private LocalDateTime modDate;

}

/*
    JPA 내부에서 엔티티 객체가 생성/변경되는 것을 감지하는 역할을 AuditingEntityListener로 이루어진다.
    AuditingEntityListener를 활성하시키려면 프로젝트에 @EnableJpaAuditing을 설정해야한다. -> GusetBookApplication

    JPA Auditing이란?
    Java에서 ORM 기술인 JPA를 사용하여 도메인을 관계형 데이터베이스 테이블에 매핑할 때 공통적으로 도메인들이 가지고 있는 필드나 컬럼들이 존재합니다.
    대표적으로 생성일자, 수정일자, 식별자 같은 필드 및 컬럼이 있습니다.

    도메인마다 공통으로 존재한다는 의미는 결국 코드가 중복된다는 말과 일맥상통합니다.
    데이터베이스에서 누가, 언제하였는지 기록을 잘 남겨놓아야 합니다. 그렇기 때문에 생성일, 수정일 컬럼은 대단히 중요한 데이터 입니다.

    그래서 JPA에서는 Audit이라는 기능을 제공하고 있습니다. Audit은 감시하다, 감사하다라는 뜻으로 Spring Data JPA에서 시간에 대해서 자동으로 값을 넣어주는 기능입니다.
    도메인을 영속성 컨텍스트에 저장하거나 조회를 수행한 후에 update를 하는 경우 매번 시간 데이터를 입력하여 주어야 하는데,
    audit을 이용하면 자동으로 시간을 매핑하여 데이터베이스의 테이블에 넣어주게 됩니다.
 */
