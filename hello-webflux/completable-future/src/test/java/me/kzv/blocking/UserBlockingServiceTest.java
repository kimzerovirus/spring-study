package me.kzv.blocking;

import me.kzv.blocking.repository.ArticleBlockingRepository;
import me.kzv.blocking.repository.FollowBlockingRepository;
import me.kzv.blocking.repository.ImageBlockingRepository;
import me.kzv.blocking.repository.UserBlockingRepository;
import me.kzv.common.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class UserBlockingServiceTest {
    UserBlockingService userBlockingService;
    UserBlockingRepository userBlockingRepository;
    ArticleBlockingRepository articleBlockingRepository;
    ImageBlockingRepository imageBlockingRepository;
    FollowBlockingRepository followBlockingRepository;

    @BeforeEach
    void setUp() {
        userBlockingRepository = new UserBlockingRepository();
        articleBlockingRepository = new ArticleBlockingRepository();
        imageBlockingRepository = new ImageBlockingRepository();
        followBlockingRepository = new FollowBlockingRepository();

        userBlockingService = new UserBlockingService(
                userBlockingRepository, articleBlockingRepository, imageBlockingRepository, followBlockingRepository
        );
    }

    @Test
    void getUserEmptyIfInvalidUserIdIsGiven() {
        // given
        String userId = "invalid_user_id";

        // when
        Optional<User> user = userBlockingService.getUserById(userId);

        // then
        assertTrue(user.isEmpty());
    }

    @Test
    void testGetUser() {
        // given
        String userId = "1234";

        // when
        Optional<User> optionalUser = userBlockingService.getUserById(userId);

        // then
        assertFalse(optionalUser.isEmpty());
        var user = optionalUser.get();
        assertEquals(user.getName(), "taewoo");
        assertEquals(user.getAge(), 32);

        assertFalse(user.getProfileImage().isEmpty());
        var image = user.getProfileImage().get();
        assertEquals(image.getId(), "image#1000");
        assertEquals(image.getName(), "profileImage");
        assertEquals(image.getUrl(), "https://dailyone.com/images/1000");

        assertEquals(2, user.getArticleList().size());

        assertEquals(1000, user.getFollowCount());
    }
}