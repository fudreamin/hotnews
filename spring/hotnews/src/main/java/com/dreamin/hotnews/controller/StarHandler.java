package com.dreamin.hotnews.controller;

import com.dreamin.hotnews.entity.Star;
import com.dreamin.hotnews.service.StarService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/star")
public class StarHandler {

    @Autowired
    private StarService starService;

    @GetMapping("/findAll")
    public List<Star> findAll(){
        return starService.findAll();
    }

    @GetMapping("/save")
    public void save() throws IOException {
        int index=1;
        int flag=1;
        while(index<154){
            String url="http://www.ylq.com/star/list-all-------"+index+".html";
            Document document = Jsoup.connect(url).timeout(5000).get();
            Elements elements = document.getElementsByClass("fContent").select("h2");
            Map<Integer,String> map=new HashMap();
            for(Element element:elements){
                String name=element.text().replaceAll("（.*?）","");
                if(!map.containsValue(name)){
                    map.put(flag,name);
                    starService.add(new Star(1,name));
                    flag++;
                }
            }
            index++;
        }
    }
}
