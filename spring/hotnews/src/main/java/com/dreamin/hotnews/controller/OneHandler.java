package com.dreamin.hotnews.controller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;

@RestController
@RequestMapping("/one")
public class OneHandler {

    @RequestMapping("/getImage")
    public String getImage() throws IOException {
        String url = "http://wufazhuce.com/";
        Document document = Jsoup.connect(url).get();
        Elements elements = document.getElementsByClass("fp-one-imagen");
        String[] adress = new String[7];
        int index=0;
        for (Element element : elements) {
            adress[index++] = element.select("img").attr("src");
        }
        return adress[0];
    }

    @RequestMapping("/getSentence")
    public String getSentence() throws IOException {
        String url = "http://wufazhuce.com/";
        Document document = Jsoup.connect(url).get();
        Elements elements = document.getElementsByClass("fp-one-cita");
        String[] sentence = new String[7];
        int index=0;
        for (Element element : elements) {
            sentence[index++] = element.select("a").text();
        }
        return sentence[0];
    }

    @RequestMapping("/getUrl")
    public String getUrl() throws IOException {
        String url = "http://wufazhuce.com/";
        Document document = Jsoup.connect(url).get();
        Elements elements = document.getElementsByClass("carousel-inner");
//        System.out.println(elements);
        String urlAdress="";
        int index=0;
        for (Element element : elements) {
            urlAdress = element.select("a").attr("href");
            break;
        }
//        System.out.println(urlAdress);
        return urlAdress;
    }



}
