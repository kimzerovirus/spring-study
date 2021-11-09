package me.kzv.pageabletest.web.controller;

import me.kzv.pageabletest.web.service.RestApiService;
import me.kzv.pageabletest.web.vo.BoardVo;
import me.kzv.pageabletest.web.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RequestMapping("/api")
@RestController
public class RestApiController {

    @Autowired
    RestApiService restApiService;

    @PostMapping("/list")
    public Page getList(@RequestBody PageVo page) throws Exception{
        return restApiService.getList(page);
    }

    @PostMapping("/save")
    public Long save(@RequestBody BoardVo board) throws Exception{
        BoardVo test = new BoardVo();
        int i;
        for (i = 0; i < 100; i++) {
            test.setName("test" + i);
            test.setContent("test Content" + 1);

            restApiService.saveList(test);
        }

        return restApiService.saveList(board);
    }

}
