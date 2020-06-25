package com.dreamin.hotnews.service;

import com.dreamin.hotnews.entity.ZhihuData;

import java.util.List;

public interface ZhihuDataService {
    public List<ZhihuData> findAll();

    public ZhihuData findById(int id);

    public List<Integer> findByName(String name);

    public void add(ZhihuData zhihudata);

    public void deleteById(int id);

    public void updateById(ZhihuData zhihudata);
}
