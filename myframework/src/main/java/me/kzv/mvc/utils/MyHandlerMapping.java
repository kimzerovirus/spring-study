package me.kzv.mvc.utils;

public interface MyHandlerMapping {
    Object findHandler(MyHandlerKey handlerKey);
}
