package com.dreamin.hotnews.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Zhihu {
    private int id;
    private String name;
    private Date time;
    private int heat;
    private String type;
    private String address;

    private String context;//新闻内容
    private Integer comment;//评论量
    private Integer like;//点赞量
    private Integer forward;//转发量
    private double isAdvertisement;//广告可能性
    private double isBan;//封杀可能性
    private double isHot;//当前热度（阅读讨论量*0.3+评论点赞*0.7）

    public Zhihu(int id, String name, Date time, int heat, String type, String address) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.heat = heat;
        this.type = type;
        this.address = address;
    }
}
