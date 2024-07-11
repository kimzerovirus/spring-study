package me.kzv.springbean.service;

public enum BeanServiceType {
    GARBANZO_BEAN("garbanzoBeanService"),
    KIDNEY_BEAN("kidneyBeanService"),
    SOYBEAN_SERVICE("soybeanService")
    ;

    private final String beanName;

    BeanServiceType(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
