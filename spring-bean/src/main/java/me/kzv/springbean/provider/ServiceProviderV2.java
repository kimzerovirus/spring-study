package me.kzv.springbean.provider;

import me.kzv.springbean.SpringBeanApplication;
import me.kzv.springbean.service.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ServiceProviderV2 {
    private final static Map<BeanServiceType, BeanService> beanServices = new HashMap<>();

    static {
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringBeanApplication.class);
        beanServices.put(BeanServiceType.GARBANZO_BEAN, ac.getBean(GarbanzoBeanService.class));
        beanServices.put(BeanServiceType.KIDNEY_BEAN, ac.getBean(KidneyBeanService.class));
        beanServices.put(BeanServiceType.SOYBEAN_SERVICE, ac.getBean(SoybeanService.class));
    }

    public BeanService getBeanService(BeanServiceType type){
        return beanServices.get(type);
    }
}
