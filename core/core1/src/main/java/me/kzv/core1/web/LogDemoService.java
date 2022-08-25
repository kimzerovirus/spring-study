package me.kzv.core1.web;

import lombok.RequiredArgsConstructor;
import me.kzv.core1.common.MyLogger;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {

//    private final ObjectProvider<MyLogger> myLoggerObjectProvider; // proxy 사용으로 인해 필요 없어짐
    private final MyLogger myLogger;

    public void logic(String testID) {
//        MyLogger myLogger = myLoggerObjectProvider.getObject();
        myLogger.log("service id = " + testID);
    }
}
