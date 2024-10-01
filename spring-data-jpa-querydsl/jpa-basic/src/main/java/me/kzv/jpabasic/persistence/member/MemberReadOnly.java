package me.kzv.jpabasic.persistence.member;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "member")
public class MemberReadOnly {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(insertable = false, updatable = false)
    private String memberName;

    @Column(name="team_id", insertable = false, updatable = false)
    private Long teamId;
}