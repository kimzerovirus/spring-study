package me.kzv.common.entity;

public class ArticleEntity {
    private final String id;
    private final String title;
    private final String content;
    private final String userId;

    public ArticleEntity(String id, String title, String content, String userId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getUserId() {
        return userId;
    }
}
