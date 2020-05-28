package com.dreamin.hotnews.repository;

import com.dreamin.hotnews.entity.MapData;

import java.util.List;

public interface MapDataRepository {
    public List<MapData> findAll();

    public void add(MapData map);

    public void updateById(MapData map);
}
