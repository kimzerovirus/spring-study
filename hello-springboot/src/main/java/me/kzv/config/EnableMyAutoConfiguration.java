package me.kzv.config;

import me.kzv.config.autoconfig.DispatcherServletConfig;
import me.kzv.config.autoconfig.TomcatWebServerConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE )
@Import(MyAutoConfigImportSelector.class) // component scan 대상이 아닌 컴포넌트를 넣는 방법
public @interface EnableMyAutoConfiguration {
}
