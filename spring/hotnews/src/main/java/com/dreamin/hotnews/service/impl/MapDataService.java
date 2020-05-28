package com.dreamin.hotnews.service.impl;

import com.dreamin.hotnews.entity.MapData;
import com.dreamin.hotnews.repository.MapDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapDataService implements com.dreamin.hotnews.service.MapDataService {
    @Autowired
    private MapDataRepository mapRepository;

    @Override
    public List<MapData> findAll() {
        return mapRepository.findAll();
    }

    @Override
    public void add(MapData map) {
        mapRepository.add(map);
    }

    @Override
    public void updateById(MapData map) {
        mapRepository.updateById(map);
    }
}
