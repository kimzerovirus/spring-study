package com.kzv.tdd.springbootjpa_tdd.web.config.auth.dto;

import com.kzv.tdd.springbootjpa_tdd.web.domain.user.User;

import lombok.Getter;
import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;

    public SessionUser(User user){
        this.email = user.getEmail();
        this.name = user.getName();
    }
}