package com.dreamin.hotnews.service;

import com.dreamin.hotnews.entity.Weibo;
import com.dreamin.hotnews.repository.WeiboRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface WeiboService {
    public List<Weibo> findAll();

    public Weibo findById(int id);

    public void add(Weibo weibo);

    public void deleteById(int id);

    public void updateById(Weibo weibo);
}
