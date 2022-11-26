package me.kzv.simpleboard.web.repository.querydsl;

import java.util.List;

public interface CustomHashtagRepository {
    List<String> findAllHashtagNames();
}
