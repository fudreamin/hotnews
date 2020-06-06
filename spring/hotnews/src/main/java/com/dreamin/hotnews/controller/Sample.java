package com.dreamin.hotnews.controller;

import com.baidu.aip.contentcensor.AipContentCensor;
import com.baidu.aip.ocr.AipOcr;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;

public class Sample {
    //设置APPID/AK/SK
    public static final String APP_ID = "20259596";
    public static final String API_KEY = "1dHC89f7B5NkGYY0dqlgfC8U";
    public static final String SECRET_KEY = "bsRZ90e3R2aiUe9HUFUXYhe9veArHGrX";

    public static void main(String[] args) throws UnsupportedEncodingException {
        AipContentCensor client=new AipContentCensor(APP_ID,API_KEY,SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        System.out.println(getConnection(client));
    }

    public static String getConnection(AipContentCensor client){
        JSONObject response=client.textCensorUserDefined("年轻人就是互联网时代的爹，他们喜欢转发评论点赞，喜欢互动追星刷数据，还能因为一个观点跟你撕上一天，反观中老年群体就是一滩死水，他们绞尽脑汁抛出一篇文章，应和者寥寥，打起架来又没几个人上，像是自己跟自己玩。\n" +
                "\n" +
                "所以互联网公司都在舔年轻人，只有舔的好的和舔的烂的，没有不舔的。他们开会讨论出最新鲜刺激的方案，制作成产品，然后发布，跪在地上寻求天神的怜悯。\n" +
                "\n" +
                "但是舔着舔着那些幸运儿发现一个问题，这帮年轻人都没啥钱，艹了，这可怎么办？于是大家看向了中年人，问资本要钱，问企业老板要钱。塞读秒广告，塞开屏广告，塞会员，无所不用其极。\n" +
                "\n" +
                "说白了，谁能玩好这一套又舔又要的瑜伽，谁就是互联网行业的老大。目前来看冠军无疑是踢壳套，一边舔一边塞信息流，而信息流广告和那个宝差不多，要的是全社会所有私企老板的钱。\n" +
                "\n" +
                "今天的一点小思考，和各位年轻人讨论，我看着你们，满怀羡慕。（其实我也是年轻人）");
        String res=response.toString();
        return res;
    }
}