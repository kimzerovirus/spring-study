package me.kzv.server.domain;


import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Vote extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "vote_id")
    private Long id;
    private int value;
    private String username;

    private Long postId;
    private Long commentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;
}
