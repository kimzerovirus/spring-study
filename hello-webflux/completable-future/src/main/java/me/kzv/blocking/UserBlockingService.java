package me.kzv.blocking;

import lombok.RequiredArgsConstructor;
import me.kzv.blocking.repository.ArticleBlockingRepository;
import me.kzv.blocking.repository.FollowBlockingRepository;
import me.kzv.blocking.repository.ImageBlockingRepository;
import me.kzv.blocking.repository.UserBlockingRepository;
import me.kzv.common.Article;
import me.kzv.common.Image;
import me.kzv.common.User;

import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class UserBlockingService {
    private final UserBlockingRepository userBlockingRepository;
    private final ArticleBlockingRepository articleBlockingRepository;
    private final ImageBlockingRepository imageBlockingRepository;
    private final FollowBlockingRepository followBlockingRepository;

    public Optional<User> getUserById(String id) {
        return userBlockingRepository.findById(id)
                .map(user -> {
                    var image = imageBlockingRepository.findById(user.getProfileImageId())
                            .map(imageEntity -> {
                                return new Image(imageEntity.getId(), imageEntity.getName(), imageEntity.getUrl());
                            });

                    var articles = articleBlockingRepository.findAllByUserId(user.getId())
                            .stream().map(articleEntity ->
                                    new Article(articleEntity.getId(), articleEntity.getTitle(), articleEntity.getContent()))
                            .collect(Collectors.toList());

                    var followCount = followBlockingRepository.countByUserId(user.getId());

                    return new User(
                            user.getId(),
                            user.getName(),
                            user.getAge(),
                            image,
                            articles,
                            followCount
                    );
                });
    }
}
