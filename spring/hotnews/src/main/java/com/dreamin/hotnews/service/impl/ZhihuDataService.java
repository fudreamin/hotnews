package com.dreamin.hotnews.service.impl;

import com.dreamin.hotnews.entity.ZhihuData;
import com.dreamin.hotnews.repository.ZhihuDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ZhihuDataService implements com.dreamin.hotnews.service.ZhihuDataService {

    @Autowired
    private ZhihuDataRepository zhihudataRepository;

    @Override
    public List<ZhihuData> findAll() {
        return zhihudataRepository.findAll();
    }

    @Override
    public ZhihuData findById(int id) {
        return zhihudataRepository.findById(id);
    }

    @Override
    public List<Integer> findByName(String name) {
        return zhihudataRepository.findByName(name);
    }

    @Override
    public void add(ZhihuData zhihudata) {
        zhihudataRepository.add(zhihudata);
    }

    @Override
    public void deleteById(int id) {
        zhihudataRepository.deleteById(id);
    }

    @Override
    public void updateById(ZhihuData zhihudata) {
        zhihudataRepository.updateById(zhihudata);
    }
}
