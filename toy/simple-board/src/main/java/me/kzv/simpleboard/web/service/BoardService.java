package me.kzv.simpleboard.web.service;

import lombok.RequiredArgsConstructor;
import me.kzv.simpleboard.web.domain.Board;
import me.kzv.simpleboard.web.repository.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public Page<Board> findBoardList(Pageable pageable) {
        Pageable pageRequest = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1, pageable.getPageSize());

        return boardRepository.findAll(pageRequest);
    }

    public Board findBoardByIdx(Long idx) {
        return boardRepository.findById(idx).orElse(new Board()); // 0 이하의 값으로 조회시 null -> TODO optional 로 변경할것
    }
}
