package com.dreamin.hotnews.controller;

import com.baidu.aip.contentcensor.AipContentCensor;
import com.dreamin.hotnews.entity.*;
import com.dreamin.hotnews.entity.Zhihu;
import com.dreamin.hotnews.service.StarService;
import com.dreamin.hotnews.service.ZhihuDataService;
import com.dreamin.hotnews.service.ZhihuService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/zhihu")
@Component
public class ZhihuHandler {

    @Autowired
    private ZhihuService zhihuService;
    @Autowired
    private StarService starService;
    @Autowired
    private ZhihuDataService zhihuDataService;

    @GetMapping("/findAll")
    private List<Zhihu> findAll() {
        return zhihuService.findAll();
    }

    @GetMapping("/findPage/{pageNum}/{pageSize}")
    public PageInfo<Zhihu> findPage(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Zhihu> list = zhihuService.findAll();
        PageInfo<Zhihu> pageInfo = new PageInfo<Zhihu>(list);
        return pageInfo;
    }

    @GetMapping("/findDataById/{id}")
    private Zhihu findDataByAdress(@PathVariable("id") int id) throws IOException {
        Zhihu zhihu = zhihuService.findById(id);
//        String adress = zhihu.getAddress();
        AipContentCensor client = ContentClient.getInstance();
//        Document document = Jsoup.connect(adress).timeout(5000).get();
//        Elements tbody = document.getElementsByClass("txt");
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        JSONObject response = client.textCensorUserDefined(zhihu.getContext() + zhihu.getName());
        //被封概率分析
        String conclusion = response.getString("conclusion");
        if (conclusion.equals("合规")) {
            zhihu.setIsBan(2);
        } else if (conclusion.equals("疑似")) {
            zhihu.setIsBan(40);
        } else if (conclusion.equals("不合规")) {
            zhihu.setIsBan(75);
        } else {
            zhihu.setIsBan(5);
        }
        zhihuService.updateById(zhihu);
        if(zhihu.getContext().length()>140){
            zhihu.setContext(zhihu.getContext().substring(0,140)+"...");
        }
        return zhihu;
    }


    @GetMapping("findById/{id}")
    private Zhihu findById(@PathVariable("id") int id) {
        return zhihuService.findById(id);
    }

    @PostMapping("/add")
    private void add(@RequestBody Zhihu zhihu) {
        zhihuService.add(zhihu);
    }

    @DeleteMapping("/deleteById/{id}")
    private void deleteById(@PathVariable("id") int id) {
        zhihuService.deleteById(id);
    }

    @PostMapping("/updateById")
    private void updateById(@RequestBody Zhihu zhihu) {
        zhihuService.updateById(zhihu);
    }

    @Scheduled(fixedRate = 3600000)
    public void spider() throws IOException {
        String url = "https://www.zhihu.com/hot";
        Map<String, String> cookies = null;
        cookies = new HashMap<String, String>();
        cookies.put("_zap", "4a2923fd-d584-4fec-a10c-ce3289620b93");
        Document document = Jsoup.connect(url).cookies(cookies).userAgent("Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.92 Mobile Safari/537.36").get();
        Elements elements = document.getElementsByClass("css-1mx3lj4");
        Elements adresses = document.getElementsByClass("css-hi1lih");
        int id = 1;
        String name = "";
        String type = "";
        String heat_str = "";
        Date date = new Date();
        String address = "";
        for (Element element : elements) {
            //热榜数据爬取
            name = element.getElementsByClass("css-dk79m8").text();
            address = adresses.get(id - 1).select("a").attr("href");
            heat_str = element.getElementsByClass("css-1ixcu37").text();
            heat_str = heat_str.split(" ", 2)[0];
            int heat = 0;
            if (!heat_str.isEmpty() && !heat_str.equals("盐选故事")) {
                heat = Integer.parseInt(heat_str);
            } else {
                heat = 0;
            }
            //点赞、评论、转发（知乎选择使用优质问题点赞数代替转发）
            String context = "";
            int comment = 0;
            //赞同数
            int like = 0;
            //回答总数
            int forward = 0;
            document = Jsoup.connect(address).cookies(cookies).userAgent("Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.92 Mobile Safari/537.36").get();
            if (!document.getElementsByClass("QuestionHeader-content").isEmpty()) {
                context = document.getElementsByClass("QuestionHeader-content").select("div").select("div").get(4).select("span").text();
            }
            String pageContent = document.toString();
            String regex = "赞同 (\\d+)";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(pageContent);
            if (m.find()) {
                like = Integer.parseInt(m.group(1));
                if (like < 10) {
                    like *= 10000;
                }
            }
            regex = "(\\d*),*(\\d+)\\D*个回答";
            p = Pattern.compile(regex);
            m = p.matcher(pageContent);
            if (m.find()) {
                forward = Integer.parseInt(m.group(1) + m.group(2));
            }
            regex = "评论 (\\d+)";
            p = Pattern.compile(regex);
            m = p.matcher(pageContent);
            if (m.find()) {
                comment = Integer.parseInt(m.group(1));
            }
            //概率处理
            double isAdvertisement = 0;
            double isBan = 0;
            double isHot = 0;
            //广告分析
            //用户不正当途径刷数据(通过评论点赞异常判断）
            int max_num = Math.max(forward, comment);
            max_num = Math.max(max_num, like);
            int min_num = Math.max(forward, comment);
            min_num = Math.max(min_num, like);
            while ((max_num + 1) / (min_num + 1) > 100 && isAdvertisement < 60) {
                isAdvertisement = isAdvertisement + 10;
                min_num *= 10;
            }
            //平台潜在广告（通过匹配广告关键词判断）
            DFATest dfaTest = new DFATest();
            List<Star> list = starService.findAll();
            dfaTest.getKeyWordSet(list);
            dfaTest.addSensitiveWordToHashMap();
            int flag = dfaTest.getSensitiveWordNum(context + name, 1);
            if (flag > 0) {
                isAdvertisement = 40;
                flag--;
            }
            while (flag > 0 && isAdvertisement < 80) {
                isAdvertisement += 20;
                flag--;
            }
            //热度分析
            isHot = heat / 1000 * 0.1 + forward * 10 * 0.5 + comment * 50 * 0.1 + like * 0.3;
            //封禁处理（因为平台限制次数，为节约次数等用户点击再填入数据）
            ZhihuData zhihuData=new ZhihuData(1,name,heat);
            zhihuDataService.add(zhihuData);
            Zhihu zhihu = new Zhihu(id, name, new java.sql.Timestamp(date.getTime()), heat, type, address, context, comment, like, forward, isAdvertisement, isBan, isHot);
            zhihuService.updateById(zhihu);
            id++;
        }
    }

}

//        String url = "https://www.zhihu.com/hot";
//        Map<String, String> cookies = null;
//        cookies = new HashMap<String, String>();
//        cookies.put("_zap", "4a2923fd-d584-4fec-a10c-ce3289620b93");
//        Document document = Jsoup.connect(url).cookies(cookies).userAgent("Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.92 Mobile Safari/537.36").get();
//        Elements elements = document.getElementsByClass("css-1mx3lj4");
//        Elements adresses = document.getElementsByClass("css-hi1lih");
//        int id = 1;
//        String name="";
//        String type = "";
//        String heat_str="";
//        Date date = new Date();
//        String address="";
//        for (Element element : elements) {
//            //热榜数据爬取
//            name = element.getElementsByClass("css-dk79m8").text();
//            address = adresses.get(id - 1).select("a").attr("href");
//            heat_str = element.getElementsByClass("css-1ixcu37").text();
//            heat_str = heat_str.split(" ", 2)[0];
//            int heat=0;
//            if(!heat_str.isEmpty()&&!heat_str.equals("盐选故事")){
//                heat = Integer.parseInt(heat_str);
//            }else{
//                heat=0;
//            }
//            Zhihu zhihu = new Zhihu(id, name, new java.sql.Timestamp(date.getTime()), heat, type, address);
//            zhihuService.updateById(zhihu);
//            id++;
//        }
