package com.thzSirius.serviceImpl;

import com.thzSirius.service.EchoService;

/**
 * Created by THZ on 2017/11/16.
 */
public class EchoServiceImpl  implements EchoService{

    public String echo(String request) {
        return "echo:"+request;
    }
}
