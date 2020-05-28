package com.dreamin.hotnews.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Baidu {
    private int id;
    private String name;
    private Date time;
    private int heat;
    private String type;
    private String address;

}
