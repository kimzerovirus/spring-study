package me.kzv.stubs;

public class NotSingleton {
    public static NotSingleton getInstance() {
        return new NotSingleton();
    }
}
