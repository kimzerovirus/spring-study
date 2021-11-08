package me.kzv.pageabletest.web.controller;

import me.kzv.pageabletest.web.service.RestApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RestApiController {

    @Autowired
    RestApiService restApiService;

    @PostMapping("/list")
    public Page getList(Pageable pageable) throws Exception{
        return restApiService.getList(pageable);
    }

}
