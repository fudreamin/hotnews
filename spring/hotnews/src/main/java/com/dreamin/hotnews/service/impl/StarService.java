package com.dreamin.hotnews.service.impl;

import com.dreamin.hotnews.entity.Star;
import com.dreamin.hotnews.entity.Weibo;
import com.dreamin.hotnews.repository.StarRepository;
import com.dreamin.hotnews.repository.WeiboRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StarService implements com.dreamin.hotnews.service.StarService {
    @Autowired
    private StarRepository starRepository;

    @Override
    public List<Star> findAll() {
        return starRepository.findAll();
    }

    @Override
    public Star login(int id, String password) {
        return starRepository.login(id,password);
    }


    @Override
    public Star findById(int id) {
        return starRepository.findById(id);
    }

    @Override
    public void add(Star star) {
        starRepository.add(star);
    }

    @Override
    public void deleteById(int id) {
        starRepository.deleteById(id);
    }

    @Override
    public void updateById(Star star) {
        starRepository.updateById(star);
    }
}
