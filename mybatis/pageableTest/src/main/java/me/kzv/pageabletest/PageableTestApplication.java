package me.kzv.pageabletest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value={"me.kzv.**.mapper"})
@SpringBootApplication
public class PageableTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(PageableTestApplication.class, args);
    }

}
