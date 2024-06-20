package me.kzv.productservice.exception;

public class InstanceNotFoundException extends RuntimeException{
    public InstanceNotFoundException(){
        super();
    }

    public InstanceNotFoundException(String message) {
        super(message);
    }
}
