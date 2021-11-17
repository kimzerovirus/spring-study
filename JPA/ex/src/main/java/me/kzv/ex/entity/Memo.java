package me.kzv.ex.entity;

import lombok.*;

import javax.persistence.*;

/**
 * Created by kimzerovirus on 2021-11-17
 */


@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_memo")
public class Memo {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long mno;

    @Column(length = 200, nullable = false)
    private String memoText;
}
