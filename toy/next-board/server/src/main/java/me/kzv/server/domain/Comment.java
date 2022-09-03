package me.kzv.server.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Getter
@Entity
public class Comment extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;
    @Column(columnDefinition = "text")
    private String body;
    private String username;

    private Long postId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;


    @OneToMany(mappedBy = "comment")
    private List<Vote> votes = new ArrayList<>();

    protected Comment() {
    }

    @Builder
    public Comment(Long id, String body, String username, Long postId) {
        this.id = id;
        this.body = body;
        this.username = username;
        this.postId = postId;
    }

    public void changeVote(User user) {
    }
}
