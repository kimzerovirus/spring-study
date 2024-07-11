package me.kzv.springbean.service;

import org.springframework.stereotype.Service;

@Service
public class KidneyBeanService implements BeanService, SomethingService {
    @Override
    public String getKoreanName() {
        return "강낭콩";
    }
}
