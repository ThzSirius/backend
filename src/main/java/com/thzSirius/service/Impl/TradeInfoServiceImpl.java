package com.thzSirius.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.thzSirius.bean.CoinData;
import com.thzSirius.common.HttpClient;
import com.thzSirius.service.TradeInfoService;
import org.redisson.api.RBucket;
import org.redisson.api.RBuckets;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.thzSirius.Money.redisson;

/**
 * Created by THZ on 2017/12/25.
 */
@Service
public class TradeInfoServiceImpl implements TradeInfoService {

    @Override
    public String setEOSTradeInfo() {
        String result = "";
        try {
            result = HttpClient.sendGet("http://api.huobi.pro/market/trade?symbol=eosusdt");
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = JSON.parseObject(result);
        List<JSONObject> jsonObjects = (List<JSONObject>) ((JSONObject) jsonObject.get("tick")).get("data");
        for (JSONObject json : jsonObjects) {
            CoinData coinData = (CoinData) json.toJavaObject(CoinData.class);
            RBucket<CoinData> bucket = redisson.getBucket(coinData.getId());
            bucket.set(coinData);
        }
        return result;

    }

    @Override
    public List getTradeInfoPush(){
        return null;
    }

    @Override
    public List getAllTradeInfo(){
        List<CoinData> coinDataList = new ArrayList<CoinData>();
        RBuckets rBuckets = redisson.getBuckets();
        List<RBucket<Object>> rBucketList = rBuckets.find("*");
        for(RBucket rBucket:rBucketList){
            coinDataList.add( (CoinData)rBucket.get());
        }
        return  coinDataList;
    }
}
