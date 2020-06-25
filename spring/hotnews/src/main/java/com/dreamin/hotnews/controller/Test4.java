package com.dreamin.hotnews.controller;

import com.dreamin.hotnews.entity.Weibo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test4 {
    public static void main(String[] args) throws IOException {
        String root_url = "https://s.weibo.com";
        String url = "https://s.weibo.com/top/summary?Refer=top_hot&topnav=1&wvr=6";
        Document document = Jsoup.connect(url).timeout(5000).get();
        Elements tbody = document.getElementsByClass("data");
        Elements elements = tbody.select("tbody>tr");
        int id = 1;
        //遍历elements
        for (Element element : elements) {
            //新闻排行信息
            String name = element.select("a").text();//新闻名
            String adress = root_url + element.select("a").attr("href");//新闻地址
            if(adress.equals("https://s.weibo.comjavascript:void(0);")){
                adress=root_url + element.select("a").attr("href_to");
            }
            String type = element.getElementsByClass("td-03").text();//新闻状态
            String temp = element.select("span").text();
            if (temp.isEmpty()) {
                continue;
            }
            int heat = Integer.parseInt(temp);
            Date date = new Date();
            //新闻具体内容爬取
            System.out.println(adress);
            document = Jsoup.connect(adress).timeout(60000).get();
            tbody = document.getElementsByClass("m-main");
            //新闻内容爬取
            String context = null;
            if (document.getElementsByClass("card-wrap").text().isEmpty())
                context = document.getElementsByClass("txt").get(0).text();
            else{
                context = document.getElementsByClass("card-wrap").get(0).select("p").text().replaceAll("导语：","");
            }
            System.out.println(context);
            //点赞、评论和转发数
            int comment = 0;
            int like = 0;
            int forward=0;
            Elements act = document.getElementsByClass("card-act");
            if (!act.isEmpty() && act.size() != 0) {
                //            System.out.println(act.get(0).text());
                String regex = "\\D+(\\d+)\\D+(\\d+)\\D+(\\d+)\\D*(\\d+)\\D*";
                Pattern p = Pattern.compile(regex);
                Matcher m = p.matcher(act.get(0).text());
                if (m.find()) {
                    comment = Integer.parseInt(m.group(2));
                    like = Integer.parseInt(m.group(3));
                    forward=Integer.parseInt(m.group(1));
                }
            }
            //概率处理
            double isAdvertisement=0;
            double isBan=0;
            double isHot=0;
            if(id==3||"荐".equals(type)){
                isAdvertisement=100;
            }
            isHot=heat/100*0.2+forward*.05+comment*50*0.45+like*0.3;
            //保存
            Weibo weiBoNews = new Weibo(id, name, new java.sql.Timestamp(date.getTime()), heat, type, adress,context,comment,like,forward,isAdvertisement,isBan,isHot);
//            System.out.println(weiBoNews.toString());
            id++;
        }
    }
}
