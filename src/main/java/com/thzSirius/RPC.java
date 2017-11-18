package com.thzSirius;

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
        System.out.println("Test success hahahaha");
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        Socket clientSocket = null;
        ServerSocket ss=null;
        try {
            ss=new ServerSocket(8081);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true){
            try {
                clientSocket = ss.accept();
                ois=new ObjectInputStream(clientSocket.getInputStream());
                oos=new ObjectOutputStream(clientSocket.getOutputStream());
                String serviceName=ois.readUTF();
                String methodName=ois.readUTF();
                Class<?>[] parameterTypes = (Class<?>[]) ois.readObject();
                Object[] parameters=(Object[]) ois.readObject();
                Class<?> service=Class.forName(serviceName);
                Method method = service.getMethod(methodName,parameterTypes);
                oos.writeObject(method.invoke(service.newInstance(),parameters));

            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                if(oos!=null){
                    try {
                        oos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(ois!=null){
                    try {
                        ois.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(clientSocket!=null){
                    try {
                        clientSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }
}
