package me.kzv.pageabletest.web.mapper;


import java.util.List;

public interface RestApiMapper {
    List getBoardList() throws Exception;

    Long getBoardTotal() throws Exception;
}
