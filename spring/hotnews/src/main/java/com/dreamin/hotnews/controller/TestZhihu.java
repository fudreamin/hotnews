package com.dreamin.hotnews.controller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestZhihu {
    public static void main(String[] args) throws IOException {
        String pageContent="  <span><button aria-label=\"赞同 3967 \" type=\"button\" class=\"Button VoteButton VoteButton--up\"><span style=\"display:inline-flex;align-items:center\">\u200B";
        String regex = "赞同 (\\d+)";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(pageContent);
        if (m.find()) {
//            System.out.println(Integer.parseInt(m.group(1)));
        }
        Document document = Jsoup.connect("https://www.zhihu.com/question/400944842").userAgent("Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.92 Mobile Safari/537.36").get();
        System.out.println(document);
    }
}
