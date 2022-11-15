package me.kzv.simpleboard.web.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.kzv.simpleboard.web.domain.enums.BoardType;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@Getter
public class Board extends BaseTimeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private String title;
    private String subTitle;
    private String content;

    @Enumerated(EnumType.STRING)
    private BoardType boardType;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @Builder
    public Board(String title, String subTitle, String content, BoardType boardType, Member member) {
        this.title = title;
        this.subTitle = subTitle;
        this.content = content;
        this.boardType = boardType;
        this.member = member;
    }
}
