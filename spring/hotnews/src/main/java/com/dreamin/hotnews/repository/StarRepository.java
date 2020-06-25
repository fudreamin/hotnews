package com.dreamin.hotnews.repository;

import com.dreamin.hotnews.entity.Star;
import com.dreamin.hotnews.entity.Weibo;

import java.util.List;

public interface StarRepository {
    public List<Star> findAll();

    public Star findById(int id);

    public Star login(int id,String password);

    public void add(Star star);

    public void deleteById(int id);

    public void updateById(Star star);
}
