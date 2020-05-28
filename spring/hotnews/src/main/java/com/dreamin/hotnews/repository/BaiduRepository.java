package com.dreamin.hotnews.repository;

import com.dreamin.hotnews.entity.Baidu;

import java.util.List;

public interface BaiduRepository {
    public List<Baidu> findAll();

    public Baidu findById(int id);

    public void add(Baidu baidu);

    public void deleteById(int id);

    public void updateById(Baidu baidu);
}
