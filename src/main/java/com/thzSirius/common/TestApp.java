package com.thzSirius.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.thzSirius.bean.CoinData;
import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestApp {
    public static WebsocketClientEndpoint clientEndPoint;
    public static RedissonClient redisson;
    public static void main(String[] args) {
        //读取mybatis配置文件
        ApplicationContext ct = new ClassPathXmlApplicationContext("spring-mybatis.xml");
        //设置Redis...
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        redisson = Redisson.create(config);
        //get eth tradedetail to set in redis
        ExecutorService getEthThreadExecutor = Executors.newSingleThreadExecutor();
        getEthThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    // open websocket
                    clientEndPoint = new WebsocketClientEndpoint(new URI("wss://api.huobi.pro/ws"));
                    // send message to websocket
                    clientEndPoint.sendMessage("{\n" +
                            "  \"sub\": \"market.ethusdt.trade.detail\",\n" +
                            "  \"id\": \"id1\"\n" +
                            "}");
                    // add listener

                    clientEndPoint.addMessageHandler(new WebsocketClientEndpoint.MessageHandler() {
                        @Override
                        public void handleMessage(String message) {

                            if (message.contains("ping")) {
                                String pong = message.replace("ping", "pong");
                                clientEndPoint.sendMessage(pong);
                                System.out.println(message);
                            } else if(message.contains("market.ethusdt.trade.detail")){
                                JSONObject jsonObject = JSON.parseObject(message);
                                List<JSONObject> jsonObjects = (List<JSONObject>) ((JSONObject) jsonObject.get("tick")).get("data");
                                for (JSONObject json : jsonObjects) {
                                    CoinData coinData = (CoinData) json.toJavaObject(CoinData.class);
                                    RBucket<CoinData> bucket = redisson.getBucket(coinData.getId());
                                    bucket.set(coinData);
                                }
                                System.out.println(message);
                            }else{
                                System.out.println(message);
                            }
                        }

                    });

                    // wait 5 seconds for messages from websocket

                    Thread.sleep(500000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (URISyntaxException ex) {
                    System.err.println("URISyntaxException exception: " + ex.getMessage());
                }
            }
        });



    }
}