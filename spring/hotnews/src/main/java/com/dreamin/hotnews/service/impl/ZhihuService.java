package com.dreamin.hotnews.service.impl;

import com.dreamin.hotnews.entity.Zhihu;
import com.dreamin.hotnews.repository.ZhihuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ZhihuService implements com.dreamin.hotnews.service.ZhihuService {

    @Autowired
    private ZhihuRepository zhihuRepository;

    @Override
    public List<Zhihu> findAll() {
        return zhihuRepository.findAll();
    }

    @Override
    public Zhihu findById(int id) {
        return zhihuRepository.findById(id);
    }

    @Override
    public void add(Zhihu zhihu) {
        Date date=new Date();
        zhihu.setTime(new java.sql.Timestamp(date.getTime()));
        zhihuRepository.add(zhihu);
    }

    @Override
    public void deleteById(int id) {
        zhihuRepository.deleteById(id);
    }

    @Override
    public void updateById(Zhihu zhihu) {
        Date date=new Date();
        zhihu.setTime(new java.sql.Timestamp(date.getTime()));
        zhihuRepository.updateById(zhihu);
    }
}
