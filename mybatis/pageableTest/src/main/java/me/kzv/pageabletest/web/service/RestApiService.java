package me.kzv.pageabletest.web.service;

import me.kzv.pageabletest.web.mapper.RestApiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestApiService {

    @Autowired
    RestApiMapper restApiMapper;

    public Page getList(Pageable pageable) throws Exception{
        List content = restApiMapper.getBoardList();
        Long total = restApiMapper.getBoardTotal();
        return new PageImpl<>(content,pageable,total);
    }
}
