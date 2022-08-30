package me.kzv.server.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Post extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;
    private String identifier;
    private String title;

    @Column(columnDefinition = "TEXT")
    private String body;

    private String subName;
    private String username;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_id")
    private Sub sub;


    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post")
    private List<Vote> votes = new ArrayList<>();


    protected Post() {
    }

    @Builder
    public Post(String identifier, String title, String body, String subName, String username, User user, Sub sub) {
        this.identifier = identifier;
        this.title = title;
        this.body = body;
        this.subName = subName;
        this.username = username;
        this.user = user;
        this.sub = sub;
    }
}
