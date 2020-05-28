package com.dreamin.hotnews.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class TestHandler {

    @RequestMapping("/test01")
    public String test01(){
        return "Hello World";
    }

}
