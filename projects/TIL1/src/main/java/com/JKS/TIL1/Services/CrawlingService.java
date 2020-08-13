package com.JKS.TIL1.Services;

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
            
            for(Element element : list.select("div.list-block")){
                String newsTitle = element.select("div.list-titles > a > strong").text();
                // System.out.println(newsTitle);
                String newsDateTime = element.select("div.list-dated").text().split("[|]")[2].strip();
                // System.out.println(newsDateTime.split("[|]")[2].strip().split(" ")[0]);
                // System.out.println(newsDateTime.split("[|]")[2].strip().split(" ")[1]);
                String newsDate = newsDateTime.split(" ")[0];
                String newsTime = newsDateTime.split(" ")[1];

                String newsUrl = element.select("div.list-titles > a").attr("href");
                // System.out.println(newsUrl);

                NewsData newsData = new NewsData(1, newsDate, newsTime, newsTitle,newsUrl);
                newsData.getNewsDate();
                // 여기서 newsDataDAO활용 INSERT문 실행

                
                System.out.println(this.newsDataDAO.insertNews(newsData));
            }

            return;
            
        } catch(Exception e) {
            return;
        }
    }
}