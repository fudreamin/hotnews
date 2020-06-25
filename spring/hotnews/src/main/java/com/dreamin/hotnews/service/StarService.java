package com.dreamin.hotnews.service;

import com.dreamin.hotnews.entity.Star;

import java.util.List;

public interface StarService {
    public List<Star> findAll();

    public Star login(int id,String password);

    public Star findById(int id);

    public void add(Star star);

    public void deleteById(int id);

    public void updateById(Star star);
}
