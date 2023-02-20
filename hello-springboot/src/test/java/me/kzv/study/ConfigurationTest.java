package me.kzv.study;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ConfigurationTest {
    @Test
    public void configuration_fail() throws Exception {
        MyConfig myConfig = new MyConfig();
        Bean1 bean1 = myConfig.bean1();
        Bean2 bean2 = myConfig.bean2();

        Assertions.assertThat(bean1.common).isNotSameAs(bean2.common);
    }

    @Test
    public void configuration() throws Exception {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        ac.register(MyConfig.class);
        ac.refresh();

        Bean1 bean1 = ac.getBean(Bean1.class);
        Bean2 bean2 = ac.getBean(Bean2.class);

        Assertions.assertThat(bean1.common).isSameAs(bean2.common);
    }

    @Test
    public void proxyCommonMethod() throws Exception {
        MyConfigProxy myConfigProxy = new MyConfigProxy();
        Bean1 bean1 = myConfigProxy.bean1();
        Bean2 bean2 = myConfigProxy.bean2();

        Assertions.assertThat(bean1.common).isSameAs(bean2.common);
    }

    static class MyConfigProxy extends MyConfig{
        private Common common;

        @Override
        Common common() {
            if(this.common == null) this.common = super.common();
            return this.common;
        }
    }

    @Configuration(proxyBeanMethods = true) // 다른 빈 메서드를 호출해서 사용하는게 아니라면 false 로 프록시를 사용하지 않아 비용을 아낄 수 있다, 즉 싱글톤을 보장받지 않게 하는 것이다.
    // 본래 스프링은 @Configuration 이 있는 클래스를 객체로 생성할 때 CGLib 라이브러리를 사용해 프록시 패턴을 적용한다.
    static class MyConfig{
        @Bean
        Common common(){
            return new Common();
        }

        @Bean
        Bean1 bean1(){
            return new Bean1(common());
        }

        @Bean
        Bean2 bean2(){
            return new Bean2(common());
        }
    }

    static class Bean1{
        private final Common common;

        public Bean1(Common common) {
            this.common = common;
        }
    }

    static class Bean2{
        private final Common common;

        public Bean2(Common common) {
            this.common = common;
        }
    }

    static class Common{}
}
