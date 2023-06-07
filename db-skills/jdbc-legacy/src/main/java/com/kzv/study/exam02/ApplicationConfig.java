package com.kzv.study.exam02;

import com.kzv.study.exam01.Car;
import com.kzv.study.exam01.Engine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
// exam01의 car와 Engine을 사용중 setEngine을 사용하지만 ApplicationConfig2는 Autowird로 등록하므로 필요가 없다.
    @Bean
    public Car car(Engine e){
        Car c = new Car();
        c.setEngine(e);
        return c;
    }

    @Bean
    public Engine engine(){
        return new Engine();
    }
}
