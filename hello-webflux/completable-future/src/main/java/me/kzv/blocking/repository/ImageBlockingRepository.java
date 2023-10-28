package me.kzv.blocking.repository;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import me.kzv.common.entity.ImageEntity;

import java.util.Map;
import java.util.Optional;

@Slf4j
public class ImageBlockingRepository {
    private final Map<String, ImageEntity> imageMap;

    public ImageBlockingRepository() {
        imageMap = Map.of(
                "image#1000", new ImageEntity("image#1000", "profileImage", "https://dailyone.com/images/1000")
        );
    }

    @SneakyThrows
    public Optional<ImageEntity> findById(String id) {
        log.info("ImageRepository.findById: {}", id);
        Thread.sleep(1000);
        return Optional.ofNullable(imageMap.get(id));
    }
}
