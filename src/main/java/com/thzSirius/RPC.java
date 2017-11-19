package com.thzSirius;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by THZ on 2017/11/16.
 */
public class RPC {
    public static void main(String args[]){
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext(new String[]{"applicationProvider.xml"});
        context.start();
        System.out.println("按任意键退出");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
