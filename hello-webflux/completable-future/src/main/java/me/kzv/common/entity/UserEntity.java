package me.kzv.common.entity;

public class UserEntity {
    private final String id;
    private final String name;
    private final int age;
    private final String profileImageId;

    public UserEntity(String id, String name, int age, String profileImageId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.profileImageId = profileImageId;
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

    public String getProfileImageId() {
        return profileImageId;
    }
}
