package com.dreamin.hotnews.controller;

import com.dreamin.hotnews.entity.ZhihuData;
import com.dreamin.hotnews.service.ZhihuDataService;
import com.dreamin.hotnews.service.ZhihuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/zhihudata")
@Component
public class ZhihuDataHandler {
    @Autowired
    private ZhihuDataService zhihuDataService;
    @Autowired
    private ZhihuService zhihuService;

    @GetMapping("/findAll")
    public List<ZhihuData> findAll(){
        return zhihuDataService.findAll();
    }

    @GetMapping("/findByName/{id}")
    public List<Integer>  findByName(@PathVariable("id") int id){
        String name=zhihuService.findById(id).getName();
        List<Integer> list= zhihuDataService.findByName(name);
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
    public void add(@RequestBody ZhihuData zhihuData){
        zhihuDataService.add(zhihuData);
    }
}
