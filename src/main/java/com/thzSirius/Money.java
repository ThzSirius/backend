package com.thzSirius;

import com.thzSirius.bean.CoinData;
import com.thzSirius.bean.User;
import com.thzSirius.service.TradeInfoService;
import com.thzSirius.service.UserService;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by THZ on 2017/12/25.
 */
public class Money {
    public static RedissonClient redisson;

    public static void main(String args[]) {
        //配置fiddker抓取包
//        System.setProperty("http.proxyHost","192.168.234.197");
//        System.setProperty("https.proxyHost", "192.168.234.197");
//        System.setProperty("http.proxyPort", "8888");
//        System.setProperty("https.proxyPort", "8888");
        //读取mybatis配置文件
        ApplicationContext ct = new ClassPathXmlApplicationContext("spring-mybatis.xml");
        //测试Mybatis
        UserService userService = (UserService) ct.getBean("userServiceImpl");
        User user = userService.getUser(1);
        //设置Redis
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        redisson = Redisson.create(config);


        TradeInfoService tradeInfoService = (TradeInfoService) ct.getBean("tradeInfoServiceImpl");
//        tradeInfoService.setEOSTradeInfo();
        List<CoinData> lists = tradeInfoService.getAllTradeInfo();
        int sell = 0;
        int buy = 0;
        for(CoinData coinData:lists){
            if(coinData.getDirection().equals("sell")){
                sell +=Double.parseDouble(coinData.getAmount());
            }else{
                buy +=Double.parseDouble(coinData.getAmount());
            }
        }

        System.out.println("sell="+sell+"buy="+buy);

    }
}
