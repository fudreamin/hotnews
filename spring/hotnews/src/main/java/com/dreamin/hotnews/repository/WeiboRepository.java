package com.dreamin.hotnews.repository;

import com.dreamin.hotnews.entity.Weibo;

import java.util.List;

public interface WeiboRepository {
    public List<Weibo> findAll();

    public Weibo findById(int id);

    public void add(Weibo weibo);

    public void deleteById(int id);

    public void updateById(Weibo weibo);
}
