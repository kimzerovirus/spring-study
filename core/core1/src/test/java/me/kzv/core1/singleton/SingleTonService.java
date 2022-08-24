package me.kzv.core1.singleton;

public class SingleTonService {

    private static final SingleTonService instance = new SingleTonService();

    public static SingleTonService getInstance() {
        return instance;
    }

    private SingleTonService(){} // private access 로 new 키워드 사용하지 못하게 막기

    public void logic(){
        System.out.println("싱글톤 로직 호출!!");
    }
}
