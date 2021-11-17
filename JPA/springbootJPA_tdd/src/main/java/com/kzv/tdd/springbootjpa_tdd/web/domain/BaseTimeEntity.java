package com.kzv.tdd.springbootjpa_tdd.web.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

}

// 모든 Entity의 상위클래스로 Entity들의 createdDate, modifiedDate를 자동으로 관리하는 역할을 한다.
/*
    @MappedSuperclass
     - JPA Entity 클래스들이 BaseEntity를 상속할 경우 필드들도 칼럼으로 인식하게 한다.

    @EntityListeners(AuditingEntityListener.class)
     - 현재 클래스에 Auditing 기능을 포함한다.
     - Auditing기능은 Spring Data JPA에서 시간에 대해서 자동으로 값을 넣어주는 기능이다.

    @CreatedDate
     - Entity가 생성되어 저장될 때 시간이 자동으로 저장된다.

    @LastModifiedDate
     - 조회한 Entity의 값을 변경할 때 시간이 자동 저장된다.
 */
