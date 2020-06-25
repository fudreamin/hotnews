package com.dreamin.hotnews.service;

import com.dreamin.hotnews.entity.WeiboData;

import java.util.List;

public interface WeiboDataService {
    public List<WeiboData> findAll();

    public WeiboData findById(int id);

    public List<Integer> findByName(String name);

    public void add(WeiboData weibodata);

    public void deleteById(int id);

    public void updateById(WeiboData weibodata);
}
