package me.kzv.di.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Component {
    String value() default ""; // <- (value="") 속성 값 추가하는 필드
}
