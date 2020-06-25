package com.dreamin.hotnews.controller;

import com.dreamin.hotnews.entity.Star;

import java.util.*;

public class DFATest {

    private Set<String> keyWordSet = new HashSet<>();//敏感词集合
    private HashMap sensitiveWordMap;//通过DFA构建的敏感词库
    public static final int MinMatchType = 1;      //最小匹配规则
    public static final int MaxMatchType = 2;      //最大匹配规则


    public void getKeyWordSet(List<Star> list) {
        for(Star star:list){
            keyWordSet.add(star.getName());
        }
//        keyWordSet.add("彭昱畅");
//        keyWordSet.add("刘涛");
    }

    //构建敏感词库
    public void addSensitiveWordToHashMap() {
        sensitiveWordMap = new HashMap(keyWordSet.size() / 3 * 4 + 1); //初始化敏感词容器，减少扩容操作,HashMap默认加载因子是0.75
        String key = null;
        Map nowMap = null;//当前层级HashMap
        Map<String, String> newWorMap = null;
        //迭代keyWordSet
        Iterator<String> iterator = keyWordSet.iterator();
        while (iterator.hasNext()) {
            key = iterator.next();    //关键字
            nowMap = sensitiveWordMap;
            for (int i = 0; i < key.length(); i++) {
                char keyChar = key.charAt(i);       //转换成char型
                Object wordMap = nowMap.get(keyChar);       //获取

                if (wordMap != null) {        //如果存在该key，直接赋值
                    nowMap = (Map) wordMap;
                } else {     //不存在则，则构建一个map，同时将isEnd设置为0，因为他不是最后一个
                    newWorMap = new HashMap<String, String>();
                    newWorMap.put("isEnd", "0");     //不是最后一个
                    nowMap.put(keyChar, newWorMap);
                    nowMap = newWorMap;
                }

                if (i == key.length() - 1) {
                    nowMap.put("isEnd", "1");    //最后一个
                }
            }
        }
    }


    public int getSensitiveWordNum(String txt, int matchType) {
        Set<String> sensitiveWordList = new HashSet<String>();
        int sensitiveWordNum=0;
        for (int i = 0; i < txt.length(); i++) {
            int length = CheckSensitiveWord(txt, i, matchType);    //判断是否包含敏感字符
            if (length > 0) {    //存在,加入list中
                sensitiveWordNum++;
                i = i + length - 1;    //减1的原因，是因为for会自增
            }
        }
        return sensitiveWordNum;
    }


    //根据敏感词库查找敏感词
    public int CheckSensitiveWord(String txt, int beginIndex,int matchType) {
        boolean  flag = false;    //敏感词结束标识位：用于敏感词只有1位的情况
        int matchFlag = 0;     //匹配标识数默认为0
        char word = 0;
        Map nowMap = sensitiveWordMap;
        for(int i = beginIndex; i < txt.length() ; i++){
            word = txt.charAt(i);
            nowMap = (Map) nowMap.get(word);     //获取指定key
            if(nowMap != null){     //存在，则判断是否为最后一个
                matchFlag++;     //找到相应key，匹配标识+1
                if("1".equals(nowMap.get("isEnd"))){       //如果为最后一个匹配规则,结束循环，返回匹配标识数
                    flag = true;       //结束标志位为true
                    if(MinMatchType == matchType){    //最小规则，直接返回,最大规则还需继续查找
                        break;
                    }
                }
            }
            else{     //不存在，直接返回
                break;
            }
        }
        if(matchFlag < 2 || !flag){        //长度必须大于等于1，为词
            matchFlag = 0;
        }
        return matchFlag;
    }


    public static void main(String[] args) {
        String text = "#被问到女友时的表情# #彭昱畅昱畅害羞# 彭昱畅 现身#刘涛直播#间，多次被涛姐cue“家人”[允悲]“家人来了可以拍吻戏了吗”、“跟家人吃顿饭呗”、“一提到家人就紧张”、“什么时候能让我见见家人”……彭彭害羞到擦汗又手足无措的样子有点可爱哈哈！L娱乐日爆社的微博视频 \u200B";
        DFATest dfaTest = new DFATest();
        dfaTest.getKeyWordSet(new ArrayList<>());
        dfaTest.addSensitiveWordToHashMap();
        int a = dfaTest.getSensitiveWordNum(text,1);
        System.out.println(a);

        String k="fdjlsf";
        k.contains("fsd");
    }
}
