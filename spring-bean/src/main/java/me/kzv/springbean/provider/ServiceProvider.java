package me.kzv.springbean.provider;

import me.kzv.springbean.service.BeanService;
import me.kzv.springbean.service.BeanServiceType;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ServiceProvider {
    private final Map<String, BeanService> beanServices;

    public ServiceProvider(Map<String, BeanService> beanServices) {
        this.beanServices = beanServices;
    }

    public void printAllBeanService(){
        System.out.println(beanServices);
    }

    public BeanService getBeanService(BeanServiceType type){
        return beanServices.get(type.getBeanName());
    }
}
