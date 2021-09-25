package org.kzv.study.sample;

import lombok.Data;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@Data
public class Chef {

    @Setter(onMethod_ = @Autowired)
    private Chef chef;

}
