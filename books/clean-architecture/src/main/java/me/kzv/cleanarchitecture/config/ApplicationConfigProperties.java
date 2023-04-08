package me.kzv.cleanarchitecture.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "cleanarchitecture")
public class ApplicationConfigProperties {

    private long transferThreshold = Long.MAX_VALUE;

}
