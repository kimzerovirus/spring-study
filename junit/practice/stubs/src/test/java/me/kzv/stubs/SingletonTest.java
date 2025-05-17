package me.kzv.stubs;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
