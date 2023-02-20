package me.kzv.config.autoconfig;

import me.kzv.config.ConditionalMyOnClass;
import me.kzv.config.EnableMyConfigurationProperties;
import me.kzv.config.MyAutoConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.ClassUtils;

//@Conditional(TomcatWebServerConfig.TomcatCondition.class)
@ConditionalMyOnClass("org.apache.catalina.startup.Tomcat")
@MyAutoConfiguration
@EnableMyConfigurationProperties(ServerProperties.class) // ServerProperties 빈으로 등록하는 또 다른 방법 @Import
public class TomcatWebServerConfig {

    @ConditionalOnMissingBean // 우리가 직접 만든 ServletWebServerFactory 타입의 빈이 있는지 확인하여 없으면 적용
    @Bean("tomcatWebServerFactory")
    public ServletWebServerFactory servletWebServerFactory(ServerProperties properties) {
        TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        serverFactory.setContextPath(properties.getContextPath());
        serverFactory.setPort(properties.getPort());
        return serverFactory;
    }

    static class TomcatCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return ClassUtils.isPresent("org.apache.catalina.startup.Tomcat", context.getClassLoader());
        }
    }
}
