package com.dreamin.hotnews.controller;

import com.dreamin.hotnews.entity.MapData;
import com.dreamin.hotnews.service.MapDataService;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

@RestController
@RequestMapping("/map")
public class MapdataHandler {

    @Autowired
    private MapDataService mapDataService;

    @RequestMapping("/findAll")
    public List<MapData> findAll() throws IOException {
        return mapDataService.findAll();
    }

    @RequestMapping("/getData")
    public void getData() throws IOException {
//        String url = "https://wp.m.163.com/163/page/news/virus_report/index.html?_nw_=1&_anw_=1";
        String url = "http://huodong.myzaker.com/h/2019ncov/map.php?need_user_info=y";
        // HtmlUnit 模拟浏览器
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setJavaScriptEnabled(true);              // 启用JS解释器，默认为true
        webClient.getOptions().setCssEnabled(false);                    // 禁用css支持
        webClient.getOptions().setThrowExceptionOnScriptError(true);   // js运行错误时，是否抛出异常
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setTimeout(3000);                   // 设置连接超时时间
        HtmlPage page = webClient.getPage(url);
        webClient.waitForBackgroundJavaScript(3000);               // 等待js后台执行3秒

        String pageAsXml = page.asXml();

        // Jsoup解析处理
        Document document = Jsoup.parse(pageAsXml, "https://wp.m.163.com/163/page/news/virus_report/index.html?_nw_=1&_anw_=1");
//        System.out.println(document);
        Elements elements = document.getElementsByClass("list-item");
        String name = null;
        String temp = null;
        int num = 0;
        MapData map;
        int id = 1;
        for (Element element : elements) {
            if (id > 34) {
                break;
            }
            name = element.select("span").get(0).text();
            temp = element.select("span").get(1).text();
            num = Integer.parseInt(temp);
            map = new MapData(id++, name, num);
            mapDataService.updateById(map);
//            System.out.println(map.toString());
//            System.out.println(id++);
        }

    }
}


