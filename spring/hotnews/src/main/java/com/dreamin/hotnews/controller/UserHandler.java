package com.dreamin.hotnews.controller;

import com.dreamin.hotnews.entity.User;
import com.dreamin.hotnews.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserHandler {

    @Autowired
    private UserService userService;

    @GetMapping("/findAll")
    private List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/findPage/{pageNum}/{pageSize}")
    public PageInfo<User> findPage(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> list = userService.findAll();
        PageInfo<User> pageInfo = new PageInfo<User>(list);
        return pageInfo;
    }

    @GetMapping("findById/{id}")
    private User findById(@PathVariable("id") int id) {
        return userService.findById(id);
    }

    @PostMapping("/add")
    private void add(@RequestBody User user) {
        userService.add(user);
    }

    @DeleteMapping("/deleteById/{id}")
    private void deleteById(@PathVariable("id") int id) {
        userService.deleteById(id);
    }

    @PostMapping("/updateById")
    private void updateById(@RequestBody User user) {
        userService.updateById(user);
    }

    @GetMapping("login/{id}/{password}")
    private boolean login(@PathVariable("id") int id, @PathVariable("password") String password) {
        User user = userService.login(id, password);
        if (user == null) {
            return false;
        }
        return true;
    }
}
