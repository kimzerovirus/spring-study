package me.kzv.pageabletest.web.service;

import lombok.extern.slf4j.Slf4j;
import me.kzv.pageabletest.web.mapper.RestApiMapper;
import me.kzv.pageabletest.web.vo.BoardVo;
import me.kzv.pageabletest.web.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RestApiService {

    @Autowired
    RestApiMapper restApiMapper;

    public Page getList(PageVo page) throws Exception{
        PageRequest pageable = PageRequest.of(page.getPageNumber(), page.getPageSize());
        log.info(String.valueOf(pageable));

        List content = restApiMapper.getBoardList(pageable);
        Long total = restApiMapper.getBoardTotal();
        return new PageImpl<>(content,pageable,total);
    }

    public Long saveList(BoardVo board) throws Exception{
        return restApiMapper.saveBoardList(board);
    }
}
