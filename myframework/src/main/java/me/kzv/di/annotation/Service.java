package me.kzv.di.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE}) // 클래스에 붙일것이므로 TYPE 으로 지정
@Retention(RetentionPolicy.RUNTIME) // 런타임 동안 유지
public @interface Service {
}
