package com.dreamin.hotnews.service.impl;

import com.dreamin.hotnews.entity.Weibo;
import com.dreamin.hotnews.repository.WeiboRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class WeiboService implements com.dreamin.hotnews.service.WeiboService {

    @Autowired
    private WeiboRepository weiboRepository;

    @Override
    public List<Weibo> findAll() {
        return weiboRepository.findAll();
    }

    @Override
    public Weibo findById(int id) {
        return weiboRepository.findById(id);
    }

    @Override
    public void add(Weibo weibo) {
        Date date=new Date();
        weibo.setTime(new java.sql.Timestamp(date.getTime()));
        weiboRepository.add(weibo);
    }

    @Override
    public void deleteById(int id) {
        weiboRepository.deleteById(id);
    }

    @Override
    public void updateById(Weibo weibo) {
        Date date=new Date();
        weibo.setTime(new java.sql.Timestamp(date.getTime()));
        weiboRepository.updateById(weibo);
    }
}
