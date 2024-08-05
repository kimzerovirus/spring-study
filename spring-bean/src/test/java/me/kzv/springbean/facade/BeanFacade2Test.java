package me.kzv.springbean.facade;

import me.kzv.springbean.service.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BeanFacade2Test {
    @Autowired
    BeanFacade2 beanFacade;

    @Autowired
    ApplicationContext ac;

    @MethodSource("beanServiceProvider")
    @ParameterizedTest
    public void test_1(BeanServiceType type, Class<BeanService> clazz) {
        BeanService beanService = ac.getBean(clazz);

        assertThat(beanFacade.getBeanService(type)).isEqualTo(beanService);
    }

    public static Stream<Arguments> beanServiceProvider() {
        return Stream.of(
                Arguments.of(BeanServiceType.GARBANZO_BEAN, GarbanzoBeanService.class),
                Arguments.of(BeanServiceType.KIDNEY_BEAN, KidneyBeanService.class),
                Arguments.of(BeanServiceType.SOYBEAN_SERVICE, SoybeanService.class)
        );
    }

    @MethodSource("beanNameProvider")
    @ParameterizedTest
    public void test_2(BeanServiceType type, String expected) {
        String bean = beanFacade.beanName(type);

        assertThat(bean).isEqualTo(expected);
    }

    public static Stream<Arguments> beanNameProvider() {
        return Stream.of(
                Arguments.of(BeanServiceType.GARBANZO_BEAN, "병아리콩"),
                Arguments.of(BeanServiceType.KIDNEY_BEAN, "강낭콩"),
                Arguments.of(BeanServiceType.SOYBEAN_SERVICE, "대두")
        );
    }
}