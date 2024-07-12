package me.kzv.springbean.facade;

import me.kzv.springbean.provider.ServiceProviderV2;
import me.kzv.springbean.service.BeanService;
import me.kzv.springbean.service.BeanServiceType;
import org.springframework.stereotype.Component;

@Component
public class BeanFacade {
    private final ServiceProviderV2 serviceProvider;

    public BeanFacade(ServiceProviderV2 serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    public String beanName(BeanServiceType type) {
        BeanService beanService = serviceProvider.getBeanService(type);
        return beanService.getKoreanName();
    }
}
