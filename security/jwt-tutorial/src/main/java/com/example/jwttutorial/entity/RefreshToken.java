package com.example.jwttutorial.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RefreshToken {

    @Id
    private String key;

    private String value; // member id


    public RefreshToken update(String token) {
        this.value = token;
        return this;
    }
}
