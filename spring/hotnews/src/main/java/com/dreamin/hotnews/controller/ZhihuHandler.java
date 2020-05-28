package com.dreamin.hotnews.controller;

import com.dreamin.hotnews.entity.Zhihu;
import com.dreamin.hotnews.service.ZhihuService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/zhihu")
@Component
public class ZhihuHandler {

    @Autowired
    private ZhihuService zhihuService;

    @GetMapping("/findAll")
    private List<Zhihu> findAll() {
        return zhihuService.findAll();
    }

    @GetMapping("/findPage/{pageNum}/{pageSize}")
    public PageInfo<Zhihu> findPage(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Zhihu> list = zhihuService.findAll();
        PageInfo<Zhihu> pageInfo = new PageInfo<Zhihu>(list);
        return pageInfo;
    }

    @GetMapping("findById/{id}")
    private Zhihu findById(@PathVariable("id") int id) {
        return zhihuService.findById(id);
    }

    @PostMapping("/add")
    private void add(@RequestBody Zhihu zhihu) {
        zhihuService.add(zhihu);
    }

    @DeleteMapping("/deleteById/{id}")
    private void deleteById(@PathVariable("id") int id) {
        zhihuService.deleteById(id);
    }

    @PostMapping("/updateById")
    private void updateById(@RequestBody Zhihu zhihu) {
        zhihuService.updateById(zhihu);
    }

    @Scheduled(fixedRate = 50000)
    public void spider() throws IOException {
        String url = "https://www.zhihu.com/hot";
        Map<String, String> cookies = null;
        cookies = new HashMap<String, String>();
        cookies.put("_zap", "4a2923fd-d584-4fec-a10c-ce3289620b93");
        Document document = Jsoup.connect(url).cookies(cookies).userAgent("Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.92 Mobile Safari/537.36").get();
        Elements elements = document.getElementsByClass("css-1mx3lj4");
        Elements adresses = document.getElementsByClass("css-hi1lih");
        int id = 1;
        String name;
        String type = "";
        String heat_str;
        Date date = new Date();
        String address;
        for (Element element : elements) {
            name = element.getElementsByClass("css-dk79m8").text();
            address = adresses.get(id - 1).select("a").attr("href");
            heat_str = element.getElementsByClass("css-1ixcu37").text();
            heat_str = heat_str.split(" ", 2)[0];
            int heat;
            if(!heat_str.isEmpty()){
                heat = Integer.parseInt(heat_str);
            }else{
                heat=0;
            }
            Zhihu zhihu = new Zhihu(id, name, new java.sql.Timestamp(date.getTime()), heat, type, address);
            zhihuService.updateById(zhihu);
            id++;
        }

    }

}
