package com.dreamin.hotnews.controller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class JudgeTest {
    public static void main(String[] args) throws IOException {
        String url = "https://s.weibo.com/weibo?q=%23%E5%BC%A0%E9%93%AD%E6%81%A9%E5%8F%91%E6%96%87%23&Refer=top";
        Document document = Jsoup.connect(url).get();
        Elements elements=document.getElementsByClass("txt");
        String text=elements.text();
        System.out.println(text);
    }
}
