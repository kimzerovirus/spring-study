package me.kzv.sns.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.kzv.sns.model.enums.UserRole;
import me.kzv.sns.model.entity.UserEntity;

import java.sql.Timestamp;

// DTO
@Getter
@AllArgsConstructor
public class User {
    private Integer id;
    private String username;
    private String password;
    private UserRole userRole;
    private Timestamp registeredAt;
    private Timestamp updatedAt;
    private Timestamp deletedAt;


    public static User fromEntity(UserEntity entity) {
        return new User(
                entity.getId(),
                entity.getUserName(),
                entity.getPassword(),
                entity.getRole(),
                entity.getRegisteredAt(),
                entity.getUpdatedAt(),
                entity.getRemovedAt()
        );
    }

}
