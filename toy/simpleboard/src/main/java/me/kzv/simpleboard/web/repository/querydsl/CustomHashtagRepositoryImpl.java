package me.kzv.simpleboard.web.repository.querydsl;

import me.kzv.simpleboard.web.domain.Hashtag;
import me.kzv.simpleboard.web.domain.QHashtag;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class CustomHashtagRepositoryImpl extends QuerydslRepositorySupport implements CustomHashtagRepository {

    public CustomHashtagRepositoryImpl() {
        super(Hashtag.class);
    }

    @Override
    public List<String> findAllHashtagNames() {
        QHashtag hashtag = QHashtag.hashtag;

        return from(hashtag)
                .select(hashtag.hashtagName)
                .fetch();
    }
}
