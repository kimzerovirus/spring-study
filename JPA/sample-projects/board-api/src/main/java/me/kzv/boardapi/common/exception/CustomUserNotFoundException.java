package me.kzv.boardapi.common.exception;


public class CustomUserNotFoundException extends RuntimeException {
    public CustomUserNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }

    public CustomUserNotFoundException(String msg) {
        super(msg);
    }

    public CustomUserNotFoundException() {
        super();
    }
}