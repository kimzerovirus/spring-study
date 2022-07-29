package me.kzv.shopapi;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ShopApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopApiApplication.class, args);
    }

    @Bean
    Hibernate5Module hibernate5Module(){
        /** 지연 로딩 강제 설정 */
//        Hibernate5Module hibernate5Module = new Hibernate5Module();
//        hibernate5Module.configure(Hibernate5Module.Feature.FORCE_LAZY_LOADING, true);

        /** 이렇게 그냥 반환하면 지연로딩을 무시하고 바로 반환해 준다. */
        return new Hibernate5Module();
    }
}
