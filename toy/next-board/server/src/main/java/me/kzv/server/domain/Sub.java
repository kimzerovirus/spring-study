package me.kzv.server.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Sub extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "sub_id")
    private Long id;
    private String name;
    private String title;

    @Column(columnDefinition = "text")
    private String description;

    private String imageUrn;
    private String bannerUrn;
    private String username;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    @OneToMany(mappedBy = "sub")
    private List<Post> posts = new ArrayList<>();

}
