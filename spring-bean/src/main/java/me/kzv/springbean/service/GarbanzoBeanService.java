package me.kzv.springbean.service;

import org.springframework.stereotype.Service;

@Service
public class GarbanzoBeanService implements BeanService, SomethingService {
    @Override
    public String getKoreanName() {
        return "병아리콩";
    }
}
