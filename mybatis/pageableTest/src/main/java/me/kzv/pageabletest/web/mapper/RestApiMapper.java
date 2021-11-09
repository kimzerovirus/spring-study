package me.kzv.pageabletest.web.mapper;


import me.kzv.pageabletest.web.vo.BoardVo;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RestApiMapper {
    List getBoardList(Pageable pageable) throws Exception;

    Long getBoardTotal() throws Exception;

    Long saveBoardList(BoardVo board) throws Exception;
}
