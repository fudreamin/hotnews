package com.dreamin.hotnews.service;

import com.dreamin.hotnews.entity.User;

import java.util.List;

public interface UserService {
    public List<User> findAll();

    public User login(int id,String password);

    public User findById(int id);

    public void add(User user);

    public void deleteById(int id);

    public void updateById(User user);
}
