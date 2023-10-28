package me.kzv.common;

public class Image {
    private final String id;
    private final String name;
    private final String url;

    public Image(String id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}