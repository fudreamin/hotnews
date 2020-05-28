package com.dreamin.hotnews.repository;

import com.dreamin.hotnews.entity.Zhihu;

import java.util.List;

public interface ZhihuRepository {
    public List<Zhihu> findAll();

    public Zhihu findById(int id);

    public void add(Zhihu zhihu);

    public void deleteById(int id);

    public void updateById(Zhihu zhihu);
}
