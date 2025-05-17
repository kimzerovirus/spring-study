package me.kzv.stubs.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SingletonTest {

    @DisplayName("여러번 인스턴스를 가져와도 같은 객체이다.")
    @Test
    public void singletonTest1() {
        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();
        Singleton singleton3 = Singleton.getInstance();

        assertEquals(singleton1, singleton2);
        assertEquals(singleton2, singleton3);
        assertEquals(singleton3, singleton1);

        singleton1 = Singleton.getInstance2();
        singleton2 = Singleton.getInstance2();
        singleton3 = Singleton.getInstance2();

        assertEquals(singleton1, singleton2);
        assertEquals(singleton2, singleton3);
        assertEquals(singleton3, singleton1);
    }
    
    @DisplayName("생성되는 객체는 모두 다르다.")
    @Test
    public void notSingletonTest1() {
        NotSingleton notSingleton1 = NotSingleton.getInstance();
        NotSingleton notSingleton2 = NotSingleton.getInstance();
        NotSingleton notSingleton3 = NotSingleton.getInstance();

        assertNotEquals(notSingleton1, notSingleton2);
        assertNotEquals(notSingleton2, notSingleton3);
        assertNotEquals(notSingleton3, notSingleton1);
    }
}
