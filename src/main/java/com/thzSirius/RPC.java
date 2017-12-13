package com.thzSirius;

import com.thzSirius.bean.User;
import com.thzSirius.service.Impl.UserServiceImpl;
import com.thzSirius.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by THZ on 2017/11/16.
 */
public class RPC {

    private UserService userService;
    public static void main(String args[]){
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext(new String[]{"applicationProvider.xml"});
        context.start();
        UserService userService =new UserServiceImpl();
        User user = userService.getUser(1);
        System.out.println(user.getName()+"按任意键退出");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
