package me.kzv.core1.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 간단한 설정이 아니라면(ex 외부 모듈과 연결을 맺는 등) 생성자와 초기화는 분리하는게 좋다
 */
public class NetworkClient { //implements InitializingBean, DisposableBean { // 인터페이스를 활용한 초기화 방법은 더 이상 사용하지 않음
    private String url;

    public NetworkClient() {
        System.out.println("url = " + url);
        connect();
        call("초기화 연결 메시지");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void connect() {
        System.out.println("connect url = " + url);
    }

    public void call(String msg) {
        System.out.println("call = " + url + " msg = " + msg);
    }

    public void disconnect() {
        System.out.println("close");
    }

//    @Override
//    public void afterPropertiesSet() throws Exception {
//        connect();
//        call("초기화!!");
//    }
//
//    @Override
//    public void destroy() throws Exception {
//        disconnect();
//    }


//    @PostConstruct // 어노테이션으로 설정하기
    // 빈으로 설정하기
    public void init() {
        System.out.println("NetworkClient afterPropertiesSet");
        connect();
        call("초기화!!");
    }

//    @PreDestroy
    public void close() {
        System.out.println("NetworkClient destroy");
        disconnect();
    }
}
