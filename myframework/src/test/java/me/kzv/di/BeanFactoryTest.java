package me.kzv.di;

import me.kzv.di.annotation.Controller;
import me.kzv.di.annotation.Service;
import me.kzv.di.controller.UserController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


class BeanFactoryTest {
    private Reflections reflections;
    private BeanFactory beanFactory;

    @BeforeEach
    void setUp() {
        reflections = new Reflections("me.kzv.di"); // base package 를 인자로 받는다.
        Set<Class<?>> preInstantiatedClazz = getTypesAnnotatedWith(Controller.class, Service.class); // 애노테이션이 붙은 객체들을 받아서 리턴하는 메서드

        beanFactory = new BeanFactory(preInstantiatedClazz);
    }

    private Set<Class<?>> getTypesAnnotatedWith(Class<? extends Annotation>... annotations) { // 몇 개의 객체가 들어올지 모르므로
        Set<Class<?>> beans = new HashSet<>();

        for (Class<? extends Annotation> annotation : annotations) {
            beans.addAll(reflections.getTypesAnnotatedWith(annotation));
        }

        return beans;
    }


    @Test
    void diTest() {
        UserController userController = beanFactory.getBean(UserController.class);

        assertThat(userController).isNotNull();
        assertThat(userController.getUserService()).isNotNull();
    }
}

/**
 * 1) Reflections
 * Reflections 생성자에 넘겨주는 파라미터 값은 클래스를 찾을때의 출발 패키지이다.
 * 파라미터 값이 "com.atoz_develop"이면 com.atoz_develop 패키지와 그 하위 패키지를 모두 찾는다. 빈 문자열을 넘기면 자바 classpath에 있는 모든 패키지를 찾는다.
 *
 * 구체적인 클래스 타입을 알지 못해도 그 클래스의 정보(메서드, 타입, 변수 등등)에 접근할 수 있게 해주는 자바 API다.
 *
 * 힙 영역에 로드돼 있는 클래스 타입의 객체를 통해 필드/메소드/생성자를 접근 제어자와 상관없이 사용할 수 있는 API
 * 컴파일 시점이 아닌 런타임 시점에 동접으로 특정 클래스의 정보를 추출해 낼 수 있는 프로그래밍 기법 -> 주로 프레임워크 또는 라이브러리 개발 시 사용된다.
 *
 * 자바에서는 JVM이 실행되면 사용자가 작성한 자바 코드가 컴파일러를 거쳐 바이트 코드로 변환되어 static 영역에 저장된다.
 * Reflection API는 이 정보를 활용한다. 그래서 클래스 이름만 알고 있다면 언제든 static 영역을 뒤져서 정보를 가져올 수 있는 것이다.
 *
 * 2) getTypesAnnotatedWith()
 * 이 메소드를 호출할때 어노테이션의 클래스를 파라미터로 넘겨 해당 어노테이션이 붙은 클래스를 찾을 수 있다.
 * getTypesAnnotatedWith(Component.class)와 같이 호출하면 @Component 어노테이션이 붙은 클래스를 찾는다.
 * 반환값은 해당 어노테이션이 선언된 클래스 목록이다.
 *
 * 3) getAnnotation().value()
 * getAnnotation()을 통해 어노테이션을 추출한다.
 * 어노테이션을 추출하면 해당 어노테이션에 정의된 메소드(속성)을 호출해서 속성 값을 꺼낼 수 있다.
 * Component 어노테이션에는 value() 속성이 정의되어 있으므로 value()를 호출해서 value()에 설정된 값을 꺼낼 수 있다.
 *
 * 이렇게 꺼낸 값을 key로 하여 필요한 곳에서 사용할 수 있도록 객체를 저장한다.
 *
 * 컴퍼일 시점 : 우리가 작성한 소스코드가 컴파일러를 통해 기계가 읽을 수 있는 기계어로 변환되는 시점
 * 런타임 시점 : 컴파일 이후 프로그램이 작동하고 있을 때
 *
 */