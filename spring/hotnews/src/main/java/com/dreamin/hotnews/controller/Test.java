package com.dreamin.hotnews.controller;

import com.dreamin.hotnews.entity.Zhihu;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) throws IOException {
        String url = "https://www.zhihu.com/hot";
        Map<String, String> cookies = null;
        cookies = new HashMap<String, String>();
        cookies.put("_zap","4a2923fd-d584-4fec-a10c-ce3289620b93");
        Document document = Jsoup.connect(url).cookies(cookies).userAgent("Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.92 Mobile Safari/537.36").get();
        Elements elements = document.getElementsByClass("css-1mx3lj4");
        Elements adresses=document.getElementsByClass("css-hi1lih");
        int id=1;
        String name;
        String type="";
        String heat_str;
        Date date = new Date();
        String adress;
        for (Element element : elements) {
            name=element.getElementsByClass("css-dk79m8").text();
            adress=adresses.get(id-1).select("a").attr("href");
            heat_str=element.getElementsByClass("css-1ixcu37").text();
            heat_str=heat_str.split(" ",2)[0];
            int heat=Integer.parseInt(heat_str);
            Zhihu zhihu=new Zhihu(id,name,new java.sql.Timestamp(date.getTime()),heat,type,adress);
            System.out.println(zhihu.toString());
            id++;
        }
    }
}
