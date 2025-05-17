package me.kzv.stubs.singleton;

public class NotSingleton {
    public static NotSingleton getInstance() {
        return new NotSingleton();
    }
}
