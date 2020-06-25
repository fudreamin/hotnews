package com.dreamin.hotnews.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WeiboData {
    int id;
    String name;
    int heat;
}
