package me.kzv.message;

public class TisException extends RuntimeException {
    public TisException(TisError error) {
        super(error.getMessageKey());
    }
}
