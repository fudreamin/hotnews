package com.dreamin.hotnews.service;

import com.dreamin.hotnews.entity.Baidu;

import java.util.List;

public interface BaiduService {
    public List<Baidu> findAll();

    public Baidu findById(int id);

    public void add(Baidu baidu);

    public void deleteById(int id);

    public void updateById(Baidu baidu);
}
