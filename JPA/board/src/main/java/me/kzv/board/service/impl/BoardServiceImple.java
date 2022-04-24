package me.kzv.board.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import me.kzv.board.dto.BoardDTO;
import me.kzv.board.dto.PageRequestDTO;
import me.kzv.board.dto.PageResultDTO;
import me.kzv.board.entity.Board;
import me.kzv.board.entity.Member;
import me.kzv.board.repository.BoardRepository;
import me.kzv.board.repository.ReplyRepository;
import me.kzv.board.service.BoardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.function.Function;

@Log4j2
@RequiredArgsConstructor
@Service
public class BoardServiceImple implements BoardService {

    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;

    @Override
    public Long register(BoardDTO dto){
        log.info(dto);

        Board board = dtoToEntity(dto);

        boardRepository.save(board);

        return board.getBno();
    }

    // 게시물 목록 가져오기
    @Override
    public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO){
        log.info(pageRequestDTO);

        Function<Object[], BoardDTO> fn = (en -> entityToDTO((Board) en[0], (Member) en[1], (Long) en[2]));

//        Page<Object[]> result = boardRepository.getBoardWithReplyCount(pageRequestDTO.getPageable(Sort.by("bno").descending()));

        Page<Object[]> result = boardRepository.searchPage(
                pageRequestDTO.getType(),
                pageRequestDTO.getKeyword(),
                pageRequestDTO.getPageable(Sort.by("bno").descending())
        );


        return new PageResultDTO<>(result, fn);
    }

    // 게시물 조회
    @Override
    public BoardDTO get(Long bno){
        Object result = boardRepository.getBoardByBno(bno);

        Object[] arr = (Object[]) result;

        return entityToDTO((Board) arr[0], (Member) arr[1], (Long) arr[2]);
    }

    // 게시물 삭제
    @Transactional
    @Override
    public void removeWithReplies(Long bno) {

        // 1. 댓글 삭제
        replyRepository.deleteByBno(bno);

        // 2. 게시글 삭제
        boardRepository.deleteById(bno);
    }

    @Override
    public void modify(BoardDTO boardDTO){
        Board board = boardRepository.getById(boardDTO.getBno()); // getOne, findById

        if (board != null) {
            board.changeTitle(boardDTO.getTitle());
            board.changeContent(boardDTO.getContent());

            boardRepository.save(board);
        }
    }

}
