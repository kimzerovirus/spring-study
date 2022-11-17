package me.kzv.di;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class BeanFactory {
    private final Set<Class<?>> preInstantiatedClazz;
    private Map<Class<?>, Object> beans = new HashMap<>();

    public BeanFactory(Set<Class<?>> preInstantiatedClazz) {
        this.preInstantiatedClazz = preInstantiatedClazz;
        initialize();
    }

    private void initialize(){
        for (Class<?> clazz : preInstantiatedClazz) {
            Object instance = createInstance(clazz);
            beans.put(clazz, instance);
        }
    }

    private Object createInstance(Class<?> clazz) {
        // 생성자
        Constructor<?> constructor = findConstructor(clazz);
        
        // 파라미터
        List<Object> parameters = new ArrayList<>();
        for (Class<?> typeClass : constructor.getParameterTypes()) {
            parameters.add(getParameterByClass(typeClass));
        }

        // 인스턴스 생성
        try {
            return constructor.newInstance(parameters.toArray());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private Constructor<?> findConstructor(Class<?> clazz) {
        Constructor<?> injectedConstructor = BeanFactoryUtils.getInjectedConstructor(clazz);

        if (Objects.nonNull(injectedConstructor)) {
            return injectedConstructor;
        }

        return clazz.getConstructors()[0];
    }

    private Object getParameterByClass(Class<?> typeClass) {
        Object instanceBean = getBean(typeClass);

        if (Objects.nonNull(instanceBean)) {
            return instanceBean;
        }

        return createInstance(typeClass); // 해당 인스턴스가 없다면 생성
    }

    public <T>T getBean(Class<T> clazz) {
        return (T) beans.get(clazz);
    }

}


/**
 * 제네릭이란 JDK 1.5부터 도입한 클래스 내부에서 사용할 데이터 타입을 외부에서 지정하는 기법이다.
 * 타입 뒤에 extends 키워드를 사용해 타입을 제한시킬 수 있다 ex_ public class Car<T extends CharSequence>
 *
 *     public <T> T foo(List<T> list){ }
 *     <T> : 타입 매개변수
 *     T : 리턴 타입
 */