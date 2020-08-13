package com.JKS.TIL1.Services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

import com.JKS.TIL1.DAO.NewsDataDAO;
import com.JKS.TIL1.DTO.NewsData;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CrawlingService implements CrawlingInterface {

    @Autowired
    NewsDataDAO newsDataDAO;

    public void crawlingNews(){
        try {
            Document doc = Jsoup
                .connect("http://www.hkrecruit.co.kr/news/articleList.html?page=1&total=607&box_idxno=&sc_area=A&view_type=sm&sc_word=%EC%82%AC%EB%9E%8C%EC%9D%B8")
                .get();
            Elements list = doc
                .select("#user-container > div.float-center.max-width-1080 > div.user-content > section > article > div.article-list > section");
            
            StringBuffer test = new StringBuffer();

            for(Element element : list.select("div.list-block")){
                StringBuffer newsTitle = new StringBuffer(element.select("div.list-titles > a > strong").text());
                // System.out.println(newsTitle);
                StringBuffer newsDateTime = new StringBuffer(element.select("div.list-dated").text().split("[|]")[2].strip());
                // System.out.println(newsDateTime.split("[|]")[2].strip().split(" ")[0]);
                // System.out.println(newsDateTime.split("[|]")[2].strip().split(" ")[1]);
                StringBuffer newsDate = new StringBuffer(newsDateTime.toString().split(" ")[0]);
                StringBuffer newsTime = new StringBuffer(newsDateTime.toString().split(" ")[1]);
                StringBuffer newsUrl = new StringBuffer(element.select("div.list-titles > a").attr("href"));
                // System.out.println(newsUrl);

                LocalDate dateA = LocalDate.parse(newsDate);
                System.out.println(dateA);
                System.out.println(LocalDate.now().minusDays(1));

                if (dateA.compareTo(LocalDate.now()) >= 0){
                    continue;
                } else if(dateA.compareTo(LocalDate.now().minusDays(1))==0){
                    NewsData newsData = new NewsData(1, newsDate.toString(), newsTime.toString(), newsTitle.toString(), newsUrl.toString());
                    this.newsDataDAO.insertNews(newsData);
                } else{
                    return;
                }
            }

            return;
            
        } catch(Exception e) {
            System.out.println("오류발생");
            return;
        }
    }
}