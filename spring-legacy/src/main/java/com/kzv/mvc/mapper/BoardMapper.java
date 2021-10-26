package com.kzv.mvc.mapper;

import com.kzv.mvc.domain.BoardVO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BoardMapper {

    @Select("select * from tbl_board where bno > 0")
    public List<BoardVO> getList();

    public void insert(BoardVO board);

//    public void insertSelectKey(BoardVO board);
}
