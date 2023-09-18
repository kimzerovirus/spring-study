package com.example.redis.chaceLayer;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ApiController {

    private final UserService userService;

    /**
     * redis 에 캐싱 되는 순간 조회 속도가 더 빨라진다!
     *  @Cacheable
     *  spring.cache.type=redis 설정 필요
     *  @EnableCache 설정 필요
     */
    @GetMapping("/user/{userId}/profile")
    public UserProfile getUserProfile(@PathVariable String userId) {
        return userService.getUserProfile(userId);
    }
}
