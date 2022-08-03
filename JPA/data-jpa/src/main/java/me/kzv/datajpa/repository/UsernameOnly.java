package me.kzv.datajpa.repository;

import org.springframework.beans.factory.annotation.Value;

// DTO 로 조회 쉽게 하기
public interface UsernameOnly {
    @Value("#{target.username + ' ' + target.age}")
    String getUsername();
}