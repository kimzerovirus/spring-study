package me.kzv.config.autoconfig;

import me.kzv.config.MyAutoConfiguration;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

//@MyAutoConfiguration
public class ServerPropertiesConfig {

    // tomcat 용 jetty 용으로 하나씩 만들어서 조건 걸어줘야 하므로 다른 자동화 방법을 사용한다. - 빈 목록에 등록 안함

//    @Bean
    public ServerProperties serverProperties(Environment environment) {
//        ServerProperties properties = new ServerProperties();
//
//        properties.setContextPath(environment.getProperty("contextPath"));
//        properties.setPort(Integer.parseInt(environment.getProperty("port")));
//        return properties;

        return Binder.get(environment).bind("", ServerProperties.class).get();
    }
}
