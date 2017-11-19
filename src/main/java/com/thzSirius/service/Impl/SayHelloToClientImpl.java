package com.thzSirius.service.Impl;

import com.thzSirius.service.SayHelloToClient;

/**
 * Created by THZ on 2017/11/19.
 */
public class SayHelloToClientImpl implements SayHelloToClient {

    public String sayHello(String hello) {
        System.out.println("我成功接收到了;"+hello);
        return "我也发出了一个hello:"+hello;
    }
}
