package me.kzv.blocking.repository;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import me.kzv.common.entity.UserEntity;

import java.util.Map;
import java.util.Optional;

@Slf4j
public class UserBlockingRepository {
    private final Map<String, UserEntity> userMap;

    public UserBlockingRepository() {
        var user = new UserEntity("1234", "taewoo", 32, "image#1000");

        userMap = Map.of("1234", user);
    }

    @SneakyThrows
    public Optional<UserEntity> findById(String userId) {
        log.info("UserRepository.findById: {}", userId);
        Thread.sleep(1000);
        var user = userMap.get(userId);
        return Optional.ofNullable(user);
    }
}
