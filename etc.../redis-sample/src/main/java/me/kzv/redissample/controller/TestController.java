package me.kzv.redissample.controller;

import me.kzv.redissample.service.TestService;
import me.kzv.redissample.vo.TestVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    TestService svc;


    /**
     * value = "TestVo" : 저장될 value 로 API 의 리턴 데이터인 TestVo 객체로 선언
     * key = "#id" : 이 API 에서 id에 따라 응답값이 달라지므로 저장될 Key 로 id 파라미터 값을 선언
     * cacheManager = "cacheManager" : 위의 config 에서 작성한 cacheManager 사용
     * unless = "#id == ''" : id가 "" 일때 캐시를 저장하지 않음
     * condition = "#id.length > 2" : id의 lengrh 가 3 이상일 때만 캐시 저장
     */
//    @Cacheable(value = "TestVo", key = "#id", cacheManager = "cacheManager", unless = "#id == ''", condition = "#id.length > 2")
    @GetMapping("/get-test")
    public TestVo getData(@RequestParam String id ){

        return svc.getTestSvc(id);
    }
}
