package com.dreamin.hotnews.entity;

import com.baidu.aip.contentcensor.AipContentCensor;

public class ContentClient {
    //设置APPID/AK/SK
    public static final String APP_ID = "20259596";
    public static final String API_KEY = "1dHC89f7B5NkGYY0dqlgfC8U";
    public static final String SECRET_KEY = "bsRZ90e3R2aiUe9HUFUXYhe9veArHGrX";

    private ContentClient(){

    }

    private static class HolderClass{
        private final static AipContentCensor client=new AipContentCensor(APP_ID,API_KEY,SECRET_KEY);
    }

    public static AipContentCensor getInstance(){
        return HolderClass.client;
    }
}
