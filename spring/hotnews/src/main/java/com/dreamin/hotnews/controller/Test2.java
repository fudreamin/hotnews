package com.dreamin.hotnews.controller;

import com.dreamin.hotnews.entity.Baidu;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;

public class Test2 {

    public static void main(String[] args) throws IOException {
        String url = "http://top.baidu.com/buzz?b=341&c=513&fr=topbuzz_b1";
        Document document = Jsoup.connect(url).get();
        Elements elements = document.select("tbody>tr");
        int id = 0;
        String type = null;
        //遍历elements
        for (Element element : elements) {
            if (id == 0) {
                id++;
                continue;
            }
            String name = element.select("a").text();//新闻名
            String[] names=name.split(" ",2);
            name=names[0];
            String adress = element.select("a").attr("href");//新闻地址
            String temp = element.getElementsByClass("icon-rise").select("span").text();
            if (temp.isEmpty()) {
                temp = element.getElementsByClass("icon-fall").select("span").text();
            }
            int heat = Integer.parseInt(temp);
            Date date = new Date();
            Baidu baiduNews = new Baidu(id, name, new java.sql.Timestamp(date.getTime()), heat, type, adress);
            System.out.println(baiduNews.toString());
            id++;
        }

    }


}
