package com.kzv.study.exam03.main;

import com.kzv.study.exam03.config.ApplicationConfig;
import com.kzv.study.exam03.dao.RoleDao;
import com.kzv.study.exam03.dto.RoleDto;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JdbcTest {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        RoleDao roleDao = ac.getBean(RoleDao.class);

        RoleDto role = new RoleDto();
        role.setRoleId(2);
        role.setDescription("Hello Wrold");

//        int count = roleDao.insert(role);
//        System.out.println(count+"입력");

//        int count = roleDao.update(role);
//        System.out.println(count+"수정 완료");

        RoleDto result = roleDao.selectById(1);
        System.out.println(result);
        int del = roleDao.deleteById(2);
        System.out.println(del+"삭제 완료");
    }
}
