package com.dreamin.hotnews.service.impl;

import com.dreamin.hotnews.entity.Baidu;
import com.dreamin.hotnews.repository.BaiduRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BaiduService implements com.dreamin.hotnews.service.BaiduService {

    @Autowired
    private BaiduRepository baiduRepository;

    @Override
    public List<Baidu> findAll() {
        return baiduRepository.findAll();
    }

    @Override
    public Baidu findById(int id) {
        return baiduRepository.findById(id);
    }

    @Override
    public void add(Baidu baidu) {
        Date date=new Date();
        baidu.setTime(new java.sql.Timestamp(date.getTime()));
        baiduRepository.add(baidu);
    }

    @Override
    public void deleteById(int id) {
        baiduRepository.deleteById(id);
    }

    @Override
    public void updateById(Baidu baidu) {
        Date date=new Date();
        baidu.setTime(new java.sql.Timestamp(date.getTime()));
        baiduRepository.updateById(baidu);
    }
}

