package com.dreamin.hotnews.repository;

import com.dreamin.hotnews.entity.ZhihuData;

import java.util.List;

public interface ZhihuDataRepository {
    public List<ZhihuData> findAll();

    public ZhihuData findById(int id);

    public List<Integer> findByName(String name);

    public void add(ZhihuData zhihudata);

    public void deleteById(int id);

    public void updateById(ZhihuData zhihudata);
}
