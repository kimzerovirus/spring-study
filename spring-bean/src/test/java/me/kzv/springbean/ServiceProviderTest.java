package me.kzv.springbean;

import me.kzv.springbean.provider.ServiceProvider;
import me.kzv.springbean.provider.ServiceProviderV2;
import me.kzv.springbean.service.BeanService;
import me.kzv.springbean.service.BeanServiceType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ServiceProviderTest {
    @Autowired
    ServiceProvider serviceProvider;

    @Autowired
    ServiceProviderV2 serviceProviderV2;

    @Test
    void findAllBean() {
        serviceProvider.printAllBeanService();
    }

//    @EnumSource(BeanServiceType.class)
    @MethodSource("beanNameProvider")
    @ParameterizedTest
    public void beanServiceTest(BeanServiceType type, String expected) {
        BeanService beanService = serviceProvider.getBeanService(type);
        String bean = beanService.getKoreanName();

        assertThat(bean).isEqualTo(expected);
    }

    @MethodSource("beanNameProvider")
    @ParameterizedTest
    public void beanServiceV2Test(BeanServiceType type, String expected) {
        BeanService beanService = serviceProviderV2.getBeanService(type);
        String bean = beanService.getKoreanName();

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