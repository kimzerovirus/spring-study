package me.kzv.ex.entity;

import lombok.*;

import javax.persistence.*;

/**
 * Created by kimzerovirus on 2021-11-17
 */


@Entity
@Table(name = "tbl_memo")
@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Memo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mno;

    @Column(length = 200, nullable = false)
    private String memoText;
}
