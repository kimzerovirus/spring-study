package com.kzv.study.exam03.main;

import com.kzv.study.exam03.config.ApplicationConfig;
import com.kzv.study.exam03.dao.RoleDao;
import com.kzv.study.exam03.dto.RoleDto;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class SelectAllTest {

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        RoleDao roleDao = ac.getBean(RoleDao.class);
        List<RoleDto> list = roleDao.selectAll();

        for (RoleDto role : list) {
            System.out.println(role);
        }

    }
}
