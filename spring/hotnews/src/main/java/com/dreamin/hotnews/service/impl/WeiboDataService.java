package com.dreamin.hotnews.service.impl;

import com.dreamin.hotnews.entity.WeiboData;
import com.dreamin.hotnews.repository.WeiboDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class WeiboDataService implements com.dreamin.hotnews.service.WeiboDataService {

    @Autowired
    private WeiboDataRepository weibodataRepository;

    @Override
    public List<WeiboData> findAll() {
        return weibodataRepository.findAll();
    }

    @Override
    public WeiboData findById(int id) {
        return weibodataRepository.findById(id);
    }

    @Override
    public List<Integer> findByName(String name) {
        return weibodataRepository.findByName(name);
    }

    @Override
    public void add(WeiboData weibodata) {
        weibodataRepository.add(weibodata);
    }

    @Override
    public void deleteById(int id) {
        weibodataRepository.deleteById(id);
    }

    @Override
    public void updateById(WeiboData weibodata) {
        weibodataRepository.updateById(weibodata);
    }
}
