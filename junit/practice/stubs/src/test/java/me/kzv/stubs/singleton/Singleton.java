package me.kzv.stubs.singleton;

public class Singleton {
    private Singleton() {}

    // 싱글톤 생성 방법1 - 클래스 로딩은 JVM이 한번만 처리하므로 thread-safe하다.
    private static class Holder {
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        return Holder.INSTANCE;
    }

    // 싱글톤 생성 방법2 - 매번 메서드에 lock이 걸리므로 성능 저하 이슈 있음
    private static Singleton instance;

    public static synchronized Singleton getInstance2() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
