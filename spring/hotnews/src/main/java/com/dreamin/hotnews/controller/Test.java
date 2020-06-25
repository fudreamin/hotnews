package com.dreamin.hotnews.controller;

import com.dreamin.hotnews.entity.Star;
import com.dreamin.hotnews.entity.Zhihu;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) throws IOException {
        String url = "https://www.zhihu.com/hot";
        Map<String, String> cookies = null;
        cookies = new HashMap<String, String>();
        cookies.put("_zap", "4a2923fd-d584-4fec-a10c-ce3289620b93");
        Document document = Jsoup.connect(url).cookies(cookies).userAgent("Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.92 Mobile Safari/537.36").get();
        Elements elements = document.getElementsByClass("css-1mx3lj4");
        Elements adresses = document.getElementsByClass("css-hi1lih");
        int id = 1;
        String name = "";
        String type = "";
        String heat_str = "";
        Date date = new Date();
        String address = "";
        for (Element element : elements) {
            //热榜数据爬取
            name = element.getElementsByClass("css-dk79m8").text();
            address = adresses.get(id - 1).select("a").attr("href");
            heat_str = element.getElementsByClass("css-1ixcu37").text();
            heat_str = heat_str.split(" ", 2)[0];
            int heat = 0;
            if (!heat_str.isEmpty() && !heat_str.equals("盐选故事")) {
                heat = Integer.parseInt(heat_str);
            } else {
                heat = 0;
            }
            //点赞、评论、转发（知乎选择使用优质问题点赞数代替转发）
            String context = "";
            int comment = 0;
            //赞同数
            int like = 0;
            //回答总数
            int forward = 0;
            document = Jsoup.connect(address).cookies(cookies).userAgent("Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.92 Mobile Safari/537.36").get();
            if (!document.getElementsByClass("QuestionHeader-content").isEmpty()) {
                context = document.getElementsByClass("QuestionHeader-content").select("div").select("div").get(4).select("span").text();
//                System.out.println(context);
            }
            String pageContent = document.toString();
            String regex = "赞同 (\\d+)";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(pageContent);
            if (m.find()) {
                like = Integer.parseInt(m.group(1));
            }
            regex = "(\\d*),*(\\d+)\\D*个回答";
            p = Pattern.compile(regex);
            m = p.matcher(pageContent);
            if (m.find()) {
                forward = Integer.parseInt(m.group(1) + m.group(2));
                System.out.println("回答数" + forward);
            }
            regex = "评论 (\\d+)";
            p = Pattern.compile(regex);
            m = p.matcher(pageContent);
            if (m.find()) {
                comment = Integer.parseInt(m.group(1));
                System.out.println("评论" + comment);
            }
            //概率处理
            double isAdvertisement = 0;
            double isBan = 0;
            double isHot = 0;

            //热度分析
            isHot = heat / 1000 * 0.2 + forward * 0.5 + comment * 50 * 0.45 + like * 0.3;
            //封禁处理（因为平台限制次数，为节约次数等用户点击再填入数据）
            Zhihu zhihu = new Zhihu(id, name, new java.sql.Timestamp(date.getTime()), heat, type, address, context, comment, like, forward, isAdvertisement, isBan, isHot);
            System.out.println(zhihu.toString());
            id++;
        }
    }
}

