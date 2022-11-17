package me.kzv.mvc.view;

public interface ViewResolver {
    View resolveViewName(String viewName);
}