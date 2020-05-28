package com.dreamin.hotnews.controller;

import com.dreamin.hotnews.entity.Weibo;
import com.dreamin.hotnews.service.WeiboService;
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
@RequestMapping("/weibo")
@Component
public class WeiboHandler {

    @Autowired
    private WeiboService weiboService;

    @GetMapping("/findAll")
    private List<Weibo> findAll() {
        return weiboService.findAll();
    }

    @GetMapping("/findPage/{pageNum}/{pageSize}")
    public PageInfo<Weibo> findPage(@PathVariable ("pageNum") int pageNum, @PathVariable("pageSize") int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Weibo> list=weiboService.findAll();
        PageInfo<Weibo> pageInfo=new PageInfo<Weibo>(list);
        return pageInfo;
    }

    @GetMapping("findById/{id}")
    private Weibo findById(@PathVariable("id") int id){
        return weiboService.findById(id);
    }

    @PostMapping("/add")
    private void add(@RequestBody Weibo weibo){
        weiboService.add(weibo);
    }

    @DeleteMapping("/deleteById/{id}")
    private void deleteById(@PathVariable("id") int id){
        weiboService.deleteById(id);
    }

    @PostMapping("/updateById")
    private void updateById(@RequestBody Weibo weibo){
        weiboService.updateById(weibo);
    }

    @Scheduled(fixedRate = 500000)
    public void spider() throws IOException {
        String root_url = "https://s.weibo.com";
        String url = "https://s.weibo.com/top/summary?Refer=top_hot&topnav=1&wvr=6";
        Document document = Jsoup.connect(url).timeout(5000).get();
        Elements tbody=document.getElementsByClass("data");
        Elements elements=tbody.select("tbody>tr");
        int id = 1;
        //遍历elements
        for (Element element : elements) {
            String name=element.select("a").text();//新闻名
            String adress=root_url+element.select("a").attr("href");//新闻地址
            String type=element.getElementsByClass("td-03").text();//新闻状态
            String temp=element.select("span").text();
            if(temp.isEmpty()){
                continue;
            }
            int heat=Integer.parseInt(temp);
            Date date=new Date();
            Weibo weiBoNews=new Weibo(id,name,new java.sql.Timestamp(date.getTime()),heat,type,adress);
            weiboService.updateById(weiBoNews);
            id++;
        }
    }

}
