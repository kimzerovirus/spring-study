package me.kzv.core1.web;

import lombok.RequiredArgsConstructor;
import me.kzv.core1.common.MyLogger;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final MyLogger myLogger; // Scope 'request' is not active for the current thread --- 스프링이 올라갈 때 request 가 없는데 달라고 하니깐 에러가 남 ... 따라서 실제 클라이언트의 요청이 왔을 때로 시점을 늦추자 => Provider
//    private final ObjectProvider<MyLogger> myLoggerObjectProvider;


    @GetMapping("log-demo")
    public String logDemo(HttpServletRequest request) {
        String requestUrl = request.getRequestURL().toString();

//        MyLogger myLogger = myLoggerObjectProvider.getObject(); // 프록시 사용으로 인해 필요 x
        System.out.println(myLogger.getClass());
        myLogger.setRequestURL(requestUrl);

        myLogger.log("controller test");
        logDemoService.logic("testID");

        return "OK";
    }
}
