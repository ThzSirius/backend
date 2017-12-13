package com.thzSirius;

import com.thzSirius.bean.User;
import com.thzSirius.service.Impl.UserServiceImpl;
import com.thzSirius.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by THZ on 2017/12/13.
 */
public class Mybatis {
    public static void main(String args[]) {
        ApplicationContext ct = new ClassPathXmlApplicationContext("spring-mybatis.xml");
        UserService userService = (UserService) ct.getBean("userServiceImpl");
        User user = userService.getUser(1);
        System.out.println("11111");
    }
}
