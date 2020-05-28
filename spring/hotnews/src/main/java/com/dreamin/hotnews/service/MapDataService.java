package com.dreamin.hotnews.service;

import com.dreamin.hotnews.entity.MapData;

import java.util.List;

public interface MapDataService {
    public List<MapData> findAll();

    public void add(MapData map);

    public void updateById(MapData map);
}
