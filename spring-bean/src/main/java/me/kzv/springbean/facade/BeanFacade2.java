package me.kzv.springbean.facade;

import me.kzv.springbean.service.BeanService;
import me.kzv.springbean.service.BeanServiceType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class BeanFacade2 {
    private final Map<BeanServiceType, BeanService> beanServiceMap = new HashMap<>();

    public BeanFacade2(Set<BeanService> beanServices) {
        beanServices.forEach(beanService -> beanServiceMap.put(beanService.getBeanType(), beanService));
    }

    public BeanService getBeanService(BeanServiceType type) {
        return beanServiceMap.get(type);
    }

    public String beanName(BeanServiceType type) {
        BeanService beanService = getBeanService(type);
        return beanService.getKoreanName();
    }
}
