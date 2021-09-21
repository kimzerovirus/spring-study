package com.kzv.study.exam03.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = {"com.kzv.study.exam03.dao"}) //roledao의 레포지토리를 자동으로 빈등록해준다.
@Import({DBConfig.class})//설정 파일을 여러개로 나눠서 사용할 수 있게 해준다.
public class ApplicationConfig {
    private String driverClassname = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/JDBCEXAM?serverTimezone=Asia/Seoul&useUnicode=true&characterEncoding=utf8"; //server time zone설정 안해주면 오류남

    private String username = "root";
    private String password = "1541";

    @Bean //DataSource객체 등록하기
    public DataSource dataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driverClassname);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }

}
