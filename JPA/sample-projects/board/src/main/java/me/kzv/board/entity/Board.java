package me.kzv.board.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "writer")
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    private String title;

    private String content;

    // @ManyToOne N:1 관계 (게시판 N : 작성자 1)
    @ManyToOne(fetch = FetchType.LAZY) // 명시적으로 Lazy 로딩 지정
    private Member writer;  // 연관 관계 지정!

    public void changeTitle(String title){
        this.title = title;
    }

    public void changeContent(String content){
        this.content = content;
    }

    /*
        [ERROR] object references an unsaved transient instance - save the transient instance before flushing
        @OneToMany나 @ManyToMany인 상황에서 흔히 만나는 에러이다.
        부모 객체에서 자식 객체를 바인딩하여 한번에 저장하려는데 자식 객체가 아직 데이터 베이스에 저장되지 않았기 때문에 발생한다.

        (cascade = CascadeType.ALL) 옵션 추가


        연관관게에서 ToString은 해당클래스의 모든 멤버변수를 출력하므로 연관된 객체 역시 출력하게되므로 exclude속성으로 해당 속성을 제외하여 주는게 좋다.
     */
}
