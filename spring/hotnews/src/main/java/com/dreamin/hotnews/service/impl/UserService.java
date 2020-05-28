package com.dreamin.hotnews.service.impl;

import com.dreamin.hotnews.entity.User;
import com.dreamin.hotnews.entity.Weibo;
import com.dreamin.hotnews.repository.UserRepository;
import com.dreamin.hotnews.repository.WeiboRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements com.dreamin.hotnews.service.UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User login(int id, String password) {
        return userRepository.login(id,password);
    }


    @Override
    public User findById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public void add(User user) {
        userRepository.add(user);
    }

    @Override
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updateById(User user) {
        userRepository.updateById(user);
    }
}
