package me.kzv.springbean.service;

import org.springframework.stereotype.Service;

@Service
public class SoybeanService implements BeanService, SomethingService {
    @Override
    public String getKoreanName() {
        return "대두";
    }
}
