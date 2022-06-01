package com.kzv.study.exam02;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.kzv.study.exam02") // 사용자가 설정하지 않아도 알아서 약속된 Bean을 등록해줘!
public class ApplicationConfig2 {
}
