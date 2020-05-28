package com.dreamin.hotnews.repository;

import com.dreamin.hotnews.entity.User;
import com.dreamin.hotnews.entity.Weibo;

import java.util.List;

public interface UserRepository {
    public List<User> findAll();

    public User findById(int id);

    public User login(int id,String password);

    public void add(User user);

    public void deleteById(int id);

    public void updateById(User user);
}
