package me.kzv.sns.repository;

import me.kzv.sns.model.entity.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostEntityRepository extends JpaRepository<PostEntity, Integer> {

    public Page<PostEntity> findAllByUserId(Integer userId, Pageable pageable);

}