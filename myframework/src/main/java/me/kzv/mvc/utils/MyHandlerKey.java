package me.kzv.mvc.utils;

import me.kzv.mvc.annotation.MyRequestMethod;

import java.util.Objects;

public class MyHandlerKey {
    private String url;
    private MyRequestMethod requestMethod;

    public MyHandlerKey(String url, MyRequestMethod requestMethod) {
        this.url = url;
        this.requestMethod = requestMethod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyHandlerKey that = (MyHandlerKey) o;
        return Objects.equals(url, that.url) && requestMethod == that.requestMethod;
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, requestMethod);
    }

    @Override
    public String toString() {
        return "HandlerKey{" +
                "url='" + url + '\'' +
                ", requestMethod=" + requestMethod +
                '}';
    }
}