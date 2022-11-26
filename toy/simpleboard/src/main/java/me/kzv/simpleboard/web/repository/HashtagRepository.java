package me.kzv.simpleboard.web.repository;

import me.kzv.simpleboard.web.domain.Hashtag;
import me.kzv.simpleboard.web.repository.querydsl.CustomHashtagRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RepositoryRestResource
public interface HashtagRepository extends
        JpaRepository<Hashtag, Long>,
        CustomHashtagRepository,
        QuerydslPredicateExecutor<Hashtag>
{

    Optional<Hashtag> findByHashtagName(String hashtagName);

    List<Hashtag> findByHashtagNameIn(Set<String> hashtagNames);
}
