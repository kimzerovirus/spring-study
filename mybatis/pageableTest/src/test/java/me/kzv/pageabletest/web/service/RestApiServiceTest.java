package me.kzv.pageabletest.web.service;

import me.kzv.pageabletest.web.vo.BoardVo;
import org.junit.Test;

class RestApiServiceTest {

    RestApiService restApiService;

    @Test
    void 데이터저장(){
        int i;
        Long r;
        BoardVo board = new BoardVo();
        try {
            for (i = 0; i < 100; i++) {
                board.setName("test" + i);
                board.setContent("test Content" + 1);

                r = restApiService.saveList(board);
            }
        }catch(Exception e){

        }
    }
}