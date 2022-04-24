package me.kzv.demo.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Log4j2
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

}
