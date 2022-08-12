package me.kzv.board.repository.search;

import me.kzv.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchBoardRepository {
    Board search1();

    Page<Object[]> searchPage(String type, String keyword, Pageable pageable); //dto를 레포지토리에서 사용하지 않기위해 다 넣어줌
}
