package me.kzv.server.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class User extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;
    private String email;
    private String username;
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Vote> vote = new ArrayList<>();

    protected User() {
    }

    @Builder
    public User(String email, String username, String password, List<Post> posts, List<Vote> vote) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.posts = posts;
        this.vote = vote;
    }
}
