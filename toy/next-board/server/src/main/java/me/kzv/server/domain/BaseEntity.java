package me.kzv.server.domain;

import lombok.Getter;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
public abstract class BaseEntity {

    @Comment("생성일")
    @Column(updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Comment("수정일")
    @LastModifiedDate
    private LocalDateTime updatedAt;
}