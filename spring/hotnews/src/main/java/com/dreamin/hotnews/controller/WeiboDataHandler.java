package com.dreamin.hotnews.controller;

import com.dreamin.hotnews.entity.WeiboData;
import com.dreamin.hotnews.service.WeiboDataService;
import com.dreamin.hotnews.service.WeiboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/weibodata")
@Component
public class WeiboDataHandler {
    @Autowired
    private WeiboDataService weiboDataService;
    @Autowired
    private WeiboService weiboService;

    @GetMapping("/findAll")
    public List<WeiboData> findAll(){
        return weiboDataService.findAll();
    }

    @GetMapping("/findByName/{id}")
    public List<Integer>  findByName(@PathVariable("id") int id){
        String name=weiboService.findById(id).getName();
        List<Integer> list= weiboDataService.findByName(name);
        if(list.size()>10){
            List<Integer> list2=new ArrayList<>();
            for(int i=list.size()-10;i<list.size();i++){
                list2.add(list.get(i));
            }
            return list2;
        }else {
            return list;
        }

    }

    @PostMapping("/add")
    public void add(@RequestBody WeiboData weiboData){
        weiboDataService.add(weiboData);
    }
}
