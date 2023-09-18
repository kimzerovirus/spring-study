package com.example.redis.chaceLayer;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ExternalApiService {
    public String getUserName(String userId) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {

        }

        System.out.println("Getting user name from other service..");

        if ("A".equals(userId)) {
            return "Adam";
        }

        if ("B".equals(userId)) {
            return "Bob";
        }

        return "";
    }

    @Cacheable(cacheNames = "userAgeCache", key = "#userId") // spring-data-redis 에 포함된 캐싱 어노테이션 적용
    public int getUserAge(String userId) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {

        }

        System.out.println("Getting user age from other service..");

        if ("A".equals(userId)) {
            return 18;
        }

        if ("B".equals(userId)) {
            return 22;
        }

        return 0;
    }
}

