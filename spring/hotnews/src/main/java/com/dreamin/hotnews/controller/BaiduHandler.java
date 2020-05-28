package com.dreamin.hotnews.controller;

import com.dreamin.hotnews.entity.Baidu;
import com.dreamin.hotnews.entity.Weibo;
import com.dreamin.hotnews.service.BaiduService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/baidu")
@Component
public class BaiduHandler {

    @Autowired
    private BaiduService baiduService;

    @GetMapping("/findAll")
    private List<Baidu> findAll() {
        return baiduService.findAll();
    }

    @GetMapping("/findPage/{pageNum}/{pageSize}")
    public PageInfo<Baidu> findPage(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Baidu> list = baiduService.findAll();
        PageInfo<Baidu> pageInfo = new PageInfo<Baidu>(list);
        return pageInfo;
    }

    @GetMapping("findById/{id}")
    private Baidu findById(@PathVariable("id") int id) {
        return baiduService.findById(id);
    }

    @PostMapping("/add")
    private void add(@RequestBody Baidu baidu) {
        baiduService.add(baidu);
    }

    @DeleteMapping("/deleteById/{id}")
    private void deleteById(@PathVariable("id") int id) {
        baiduService.deleteById(id);
    }

    @PostMapping("/updateById")
    private void updateById(@RequestBody Baidu baidu) {
        baiduService.updateById(baidu);
    }

    @Scheduled(fixedRate = 500000)
    public void spider() throws IOException {
        String url = "http://top.baidu.com/buzz?b=341&c=513&fr=topbuzz_b1";
        Document document = Jsoup.connect(url).timeout(5000).get();
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
            String[] names = name.split(" ", 2);
            name = names[0];
            String address = element.select("a").attr("href");//新闻地址
            String temp = element.getElementsByClass("icon-rise").select("span").text();
            if (temp.isEmpty()) {
                temp = element.getElementsByClass("icon-fall").select("span").text();
            }
            int heat = Integer.parseInt(temp);
            Date date = new Date();
            Baidu baiduNews = new Baidu(id, name, new java.sql.Timestamp(date.getTime()), heat, type, address);
            id++;
            baiduService.updateById(baiduNews);
        }

    }

}


