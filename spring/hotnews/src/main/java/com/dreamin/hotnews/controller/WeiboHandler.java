package com.dreamin.hotnews.controller;

import com.baidu.aip.contentcensor.AipContentCensor;
import com.dreamin.hotnews.entity.*;
import com.dreamin.hotnews.service.StarService;
import com.dreamin.hotnews.service.WeiboDataService;
import com.dreamin.hotnews.service.WeiboService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/weibo")
@Component
public class WeiboHandler {

    @Autowired
    private WeiboService weiboService;
    @Autowired
    private StarService starService;
    @Autowired
    private WeiboDataService weiboDataService;

    @GetMapping("/findAll")
    private List<Weibo> findAll() {
        return weiboService.findAll();
    }

    @GetMapping("/findPage/{pageNum}/{pageSize}")
    public PageInfo<Weibo> findPage(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Weibo> list = weiboService.findAll();
        PageInfo<Weibo> pageInfo = new PageInfo<Weibo>(list);
        return pageInfo;
    }

    @GetMapping("findById/{id}")
    private Weibo findById(@PathVariable("id") int id) {
        return weiboService.findById(id);
    }

    @PostMapping("/add")
    private void add(@RequestBody Weibo weibo) {
        weiboService.add(weibo);
    }

    @DeleteMapping("/deleteById/{id}")
    private void deleteById(@PathVariable("id") int id) {
        weiboService.deleteById(id);
    }

    @PostMapping("/updateById")
    private void updateById(@RequestBody Weibo weibo) {
        weiboService.updateById(weibo);
    }

    @GetMapping("/findDataById/{id}")
    private Weibo findDataByAdress(@PathVariable("id") int id) throws IOException {
        Weibo weibo=weiboService.findById(id);
//        String adress = weibo.getAddress();
        AipContentCensor client = ContentClient.getInstance();
//        Document document = Jsoup.connect(adress).timeout(5000).get();
//        Elements tbody = document.getElementsByClass("txt");
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        JSONObject response = client.textCensorUserDefined(weibo.getContext());
        //被封概率分析
        String conclusion=response.getString("conclusion");
        if(conclusion.equals("合规")){
            weibo.setIsBan(2);
        }else if(conclusion.equals("疑似")){
            weibo.setIsBan(40);
        }else if(conclusion.equals("不合规")){
            weibo.setIsBan(75);
        }else{
            weibo.setIsBan(5);
        }
        weiboService.updateById(weibo);
        return weibo;
    }

    @Scheduled(fixedRate =3600000 )//
    public void spider() throws IOException {
        String root_url = "https://s.weibo.com";
        String url = "https://s.weibo.com/top/summary?Refer=top_hot&topnav=1&wvr=6";
        Document document = Jsoup.connect(url).timeout(5000).get();
        Elements tbody = document.getElementsByClass("data");
        Elements elements = tbody.select("tbody>tr");
        int id = 1;
        //遍历elements
        for (Element element : elements) {
            //新闻排行信息
            String name = element.select("a").text();//新闻名
            String adress = root_url + element.select("a").attr("href");//新闻地址
            if (adress.equals("https://s.weibo.comjavascript:void(0);")) {
                adress = root_url + element.select("a").attr("href_to");
            }
            String type = element.getElementsByClass("td-03").text();//新闻状态
            String temp = element.select("span").text();
            if (temp.isEmpty()) {
                continue;
            }
            int heat = Integer.parseInt(temp);
            Date date = new Date();
            //新闻具体内容爬取
//            System.out.println(adress);
            document = Jsoup.connect(adress).timeout(60000).get();
            tbody = document.getElementsByClass("m-main");
            //新闻内容爬取
            String context = "";
            if (document.getElementsByClass("txt").size()>0&&!document.getElementsByClass("txt").get(0).text().isEmpty())
                context = document.getElementsByClass("txt").get(0).text();
            context=context.replaceAll("[\\x{10000}-\\x{10FFFF}]", "");//去除表情符
            //点赞、评论和转发数
            int comment = 0;
            int like = 0;
            int forward = 0;
            Elements act = document.getElementsByClass("card-act");
            if (!act.isEmpty() && act.size() != 0) {
                //            System.out.println(act.get(0).text());
                String regex = "\\D+(\\d+)\\D+(\\d+)\\D+(\\d+)\\D*(\\d+)\\D*";
                Pattern p = Pattern.compile(regex);
                Matcher m = p.matcher(act.get(0).text());
                if (m.find()) {
                    comment = Integer.parseInt(m.group(2));
                    like = Integer.parseInt(m.group(3));
                    forward = Integer.parseInt(m.group(1));
                }
            }
            //概率处理
            double isAdvertisement = 0;
            double isBan = 0;
            double isHot = 0;
            //广告分析
            if (id == 3 || "荐".equals(type)) {//平台明示广告
                isAdvertisement = 99.8;
            }else{
                //用户不正当途径刷数据(通过评论点赞异常判断）
                int max_num=Math.max(forward,comment);
                max_num=Math.max(max_num,like);
                int min_num=Math.max(forward,comment);
                min_num=Math.max(min_num,like);
                while((max_num+1)/(min_num+1)>100&&isAdvertisement<60){
                    isAdvertisement=isAdvertisement+10;
                    min_num*=10;
                }
                //平台潜在广告（通过匹配广告关键词判断）
                DFATest dfaTest = new DFATest();
                List<Star> list=starService.findAll();
                dfaTest.getKeyWordSet(list);
                dfaTest.addSensitiveWordToHashMap();
                int flag=dfaTest.getSensitiveWordNum(context,1);
                if(flag>0){
                    isAdvertisement=30;
                    flag--;
                }
                while(flag>0&&isAdvertisement<60){
                    isAdvertisement+=15;
                    flag--;
                }
            }
            //热度分析
            isHot = heat / 1000 * 0.2 + forward * .05 + comment * 50 * 0.45 + like * 0.3;
            //封禁处理（因为平台限制次数，为节约次数等用户点击再填入数据）
            //保存
            WeiboData weiboData=new WeiboData(1,name,heat);
            weiboDataService.add(weiboData);
            Weibo weiBoNews = new Weibo(id, name, new java.sql.Timestamp(date.getTime()), heat, type, adress, context, comment, like, forward, isAdvertisement, isBan, isHot);
            weiboService.updateById(weiBoNews);
            id++;
        }
    }
}

//    @Scheduled(fixedRate = 600000)
//    public void spider() throws IOException {
//        String root_url = "https://s.weibo.com";
//        String url = "https://s.weibo.com/top/summary?Refer=top_hot&topnav=1&wvr=6";
//        Document document = Jsoup.connect(url).timeout(5000).get();
//        Elements tbody=document.getElementsByClass("data");
//        Elements elements=tbody.select("tbody>tr");
//        int id = 1;
//        //遍历elements
//        for (Element element : elements) {
//            String name=element.select("a").text();//新闻名
//            String adress=root_url+element.select("a").attr("href");//新闻地址
//            String type=element.getElementsByClass("td-03").text();//新闻状态
//            String temp=element.select("span").text();
//            if(temp.isEmpty()){
//                continue;
//            }
//            int heat=Integer.parseInt(temp);
//            Date date=new Date();
//            //新闻具体内容爬取
//            document = Jsoup.connect(url).timeout(5000).get();
//            tbody=document.getElementsByClass("m-main");
//            String context=document.getElementsByClass("card card-topic-lead s-pg16").text();
//            if(context==null) context=document.getElementsByClass("txt").get(0).text();
////            Elements act=document.getElementsByClass("card-act");
////            int comment=Integer.parseInt(act.get(0).attr("li")) ;
//            System.out.println(context);
//            Weibo weiBoNews=new Weibo(id,name,new java.sql.Timestamp(date.getTime()),heat,type,adress);
//            weiboService.updateById(weiBoNews);
//            id++;
//        }
//    }


