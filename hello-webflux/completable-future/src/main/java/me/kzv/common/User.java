package me.kzv.common;

import java.util.List;
import java.util.Optional;

public class User {
    private final String id;
    private final String name;
    private final int age;
    private final Optional<Image> profileImage;
    private final List<Article> articleList;
    private final Long followCount;

    public User(String id, String name, int age, Optional<Image> profileImage, List<Article> articleList, Long followCount) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.profileImage = profileImage;
        this.articleList = articleList;
        this.followCount = followCount;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Optional<Image> getProfileImage() {
        return profileImage;
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public Long getFollowCount() {
        return followCount;
    }
}
