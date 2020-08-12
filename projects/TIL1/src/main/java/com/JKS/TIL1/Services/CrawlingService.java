package com.JKS.TIL1.Services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class CrawlingService implements CrawlingInterface {
    public void crawlingNews(){
        try {
            Document doc = Jsoup
                .connect("http://www.hkrecruit.co.kr/news/articleList.html?page=1&total=607&box_idxno=&sc_area=A&view_type=sm&sc_word=%EC%82%AC%EB%9E%8C%EC%9D%B8")
                .get();
            Elements list = doc
                .select("#user-container > div.float-center.max-width-1080 > div.user-content > section > article > div.article-list > section");
            
            for(Element element : list.select("div.list-block")){
                String title = element.select("div.list-titles > a > strong").text();
                System.out.println(title);
            }
            
        } catch(Exception e) {
            return;
        }
    }
}