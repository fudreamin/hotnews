package com.dreamin.hotnews.controller;

import com.dreamin.hotnews.entity.Baidu;
import com.dreamin.hotnews.entity.MapData;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Test3 {
    public static void main(String[] args) throws IOException {
        String url = "https://wp.m.163.com/163/page/news/virus_report/index.html?_nw_=1&_anw_=1";
        // HtmlUnit 模拟浏览器
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setJavaScriptEnabled(true);              // 启用JS解释器，默认为true
        webClient.getOptions().setCssEnabled(false);                    // 禁用css支持
        webClient.getOptions().setThrowExceptionOnScriptError(false);   // js运行错误时，是否抛出异常
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setTimeout(10 * 1000);                   // 设置连接超时时间
        HtmlPage page = webClient.getPage(url);
        webClient.waitForBackgroundJavaScript(10 * 1000);               // 等待js后台执行30秒

        String pageAsXml = page.asXml();

        // Jsoup解析处理
        Document document = Jsoup.parse(pageAsXml, "https://wp.m.163.com/163/page/news/virus_report/index.html?_nw_=1&_anw_=1");
        System.out.println(document);
        Elements elements = document.getElementsByClass("province");
        String name = null;
        String temp = null;
        int num = 0;
        MapData map;
        int id=1;
        for (Element element : elements) {
            name = element.getElementsByClass("item_name").text();
            temp = element.getElementsByClass("item_newconfirm").text();
            num=Integer.parseInt(temp);
            map = new MapData(id,name, num);
            System.out.println(map.toString());
            System.out.println(id++);
        }

    }
}