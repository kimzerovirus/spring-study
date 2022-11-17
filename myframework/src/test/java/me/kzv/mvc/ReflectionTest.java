package me.kzv.mvc;

import me.kzv.mvc.annotation.MyController;
import me.kzv.mvc.web.model.User;
import org.junit.jupiter.api.Test;

import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class ReflectionTest {

    @Test
    void controllerScan() {
        Set<Class<?>> beans = getTypesAnnotatedWith(List.of(MyController.class));
    }

    @Test
    void showClassInfo(){
        Class<User> clazz = User.class;
        System.out.println("class name info: " + clazz.getName());

        List<Field> fields = Arrays.stream(clazz.getDeclaredFields()).collect(Collectors.toList());
        for (Field field : fields) {
            System.out.println("User all declared fields info: "+ field);
        }


    }

    private Set<Class<?>> getTypesAnnotatedWith(List<Class<? extends Annotation>> annotations) {
        Reflections reflections = new Reflections("me.kzv.mvc");

        Set<Class<?>> beans = new HashSet<>();
        annotations.forEach(annotation -> beans.addAll(reflections.getTypesAnnotatedWith(annotation)));

        return beans;
    }
}
