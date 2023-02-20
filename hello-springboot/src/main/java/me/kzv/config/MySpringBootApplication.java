package me.kzv.config;

import me.kzv.config.EnableMyAutoConfiguration;
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
@Configuration
@ComponentScan
@EnableMyAutoConfiguration
public @interface MySpringBootApplication {
}
