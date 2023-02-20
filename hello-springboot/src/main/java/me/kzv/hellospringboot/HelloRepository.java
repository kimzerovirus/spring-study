package me.kzv.hellospringboot;

public interface HelloRepository {
    Hello findHello(String name);

    void increaseCount(String name);

    default int countOf(String name) { // default 메서드 활용 공부하고 싶으면 Comparator 인터페이스 확인하기
        Hello hello = findHello(name);
        return hello == null ? 0 : hello.getCount();
    }
}
