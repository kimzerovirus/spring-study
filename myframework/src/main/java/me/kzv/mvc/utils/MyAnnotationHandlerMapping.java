package me.kzv.mvc.utils;

import me.kzv.mvc.annotation.MyRequestMapping;
import me.kzv.mvc.annotation.MyRequestMethod;
import org.reflections.Reflections;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MyAnnotationHandlerMapping implements MyHandlerMapping{
    private final Object[] basePackage;

    private final Map<MyHandlerKey, MyAnnotationHandler> handlers = new HashMap<>();

    public MyAnnotationHandlerMapping(Object... basePackage) {
        this.basePackage = basePackage;
    }

    public void initialize() {
        Reflections reflections = new Reflections(basePackage);

        Set<Class<?>> clazzesWithControllerAnnotation = reflections.getTypesAnnotatedWith(me.kzv.mvc.annotation.MyController.class, true);

        clazzesWithControllerAnnotation.forEach(clazz ->

                Arrays.stream(clazz.getDeclaredMethods()).forEach(declaredMethod -> {
                    MyRequestMapping requestMappingAnnotation = declaredMethod.getDeclaredAnnotation(MyRequestMapping.class);

                    Arrays.stream(getRequestMethods(requestMappingAnnotation))
                            .forEach(requestMethod -> handlers.put(
                                    new MyHandlerKey(requestMappingAnnotation.value(), requestMethod), new MyAnnotationHandler(clazz, declaredMethod)
                            ));

                })
        );
    }

    private MyRequestMethod[] getRequestMethods(MyRequestMapping requestMappingAnnotation) {
        return requestMappingAnnotation.method();
    }

    @Override
    public Object findHandler(MyHandlerKey handlerKey) {
        return handlers.get(handlerKey);
    }
}