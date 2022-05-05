package me.kzv.olle.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(of = "id") // 순환참조 방지?
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String nickname;

    private String password;

    // 이메일 인증
    private boolean emailVerified;

    // 이메일 검증에 사용할 토큰값
    private String emailCheckedToken;

    // 인증을 거친 사람은 그때 가입한것으로 등록
    private LocalDateTime joinedAt;

    // 자기소개
    private String bio;

    private String url;

    // 직업
    private String occupation;

    // 사는 지역
    private String location;

    @Lob // 기본 타입인 varchar(255) 보다 큰 텍스트가 들어갈 때 사용하는듯?
    @Basic(fetch = FetchType.EAGER)
    private String profileImage;

    /* 알림 설정 관련 도메인 */
    private boolean studyCreateByWeb;
    private boolean studyCreateByEmail;

    private boolean studyEnrollmentResultByWeb;
    private boolean studyEnrollmentResultByEmail;

    private boolean studyUpdatedByWeb;
    private boolean studyUpdatedByEmail;
}
