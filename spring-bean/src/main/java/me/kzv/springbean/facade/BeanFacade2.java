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

    public String beanName(BeanServiceType type) {
        BeanService beanService = beanServiceMap.get(type);
        return beanService.getKoreanName();
    }
}
