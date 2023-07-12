package me.kzv.jdbcjpas3.blog.dto;

import lombok.Getter;

@Getter
public class AddUserRequest {
    private String email;
    private String password;
}
