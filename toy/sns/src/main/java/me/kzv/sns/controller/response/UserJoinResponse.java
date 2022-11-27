package me.kzv.sns.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.kzv.sns.model.User;

@Getter
@AllArgsConstructor
public
class UserJoinResponse {
    private Integer id;
    private String name;

    public static UserJoinResponse fromUser(User user) {
        return new UserJoinResponse(
                user.getId(),
                user.getUsername()
        );
    }

}