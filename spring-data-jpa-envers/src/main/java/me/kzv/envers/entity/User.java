package me.kzv.envers.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Audited // 전체 테이블에 대해서 이력을 남기고 싶다면 클래스 상단에
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Audited(withModifiedFlag = true, modifiedColumnName = "nameChanged") // 이력 조회 대상에 대해 변경되었는지 여부를 flag 를 통해 관리할 수도 있음
    private String name;

    private String email;

    @NotAudited // 필드 중 이력을 제외하고 싶다면
    private String memo;

    // FK 만 이력 조회 대상으로 한다
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED) // FK 만 이력 조회 대상으로 한다.
    @ManyToOne(fetch = FetchType.LAZY)
    private Team team;
}
