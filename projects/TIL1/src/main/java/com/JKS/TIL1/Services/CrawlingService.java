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
            // Jsoup http요청 및 크롤링
            Document doc = Jsoup
                .connect("http://www.hkrecruit.co.kr/news/articleList.html?page=1&total=607&box_idxno=&sc_area=A&view_type=sm&sc_word=%EC%82%AC%EB%9E%8C%EC%9D%B8")
                .get();
            // 뉴스 전체를 담고있는 section
            Elements list = doc
                .select("#user-container > div.float-center.max-width-1080 > div.user-content > section > article > div.article-list > section");
            
            // String 보다는 StringBuffer나 StringBuilder를 사용할것 (String을 남발하면 메모리에 문자열이 계속 많아짐)            
            for(Element element : list.select("div.list-block")){
                StringBuffer newsTitle = new StringBuffer(element.select("div.list-titles > a > strong").text());
                StringBuffer newsDateTime = new StringBuffer(element.select("div.list-dated").text().split("[|]")[2].strip());
                StringBuffer newsDate = new StringBuffer(newsDateTime.toString().split(" ")[0]);
                StringBuffer newsTime = new StringBuffer(newsDateTime.toString().split(" ")[1]);
                StringBuffer newsUrl = new StringBuffer(element.select("div.list-titles > a").attr("href"));
                // System.out.println(newsDate);
                // System.out.println(newsTime);
                // System.out.println(newsTitle);
                // System.out.println(newsUrl);
                

                // 시간 게산을 위한 LocalDate선언 LocalDate, LocalDateTime, LocalTime 등을 지원하므로 시간계산할 때 사용해볼것
                LocalDate dateA = LocalDate.parse(newsDate);
                System.out.println(dateA);
                // 어제일자를 정확히 호출하는지 확인
                System.out.println(LocalDate.now().minusDays(1));

                // 기사가 날짜순으로 내림차순으로 정렬되므로
                // 현재날짜와 같거나 훗날인경우 continue
                if (dateA.compareTo(LocalDate.now()) >= 0){
                    continue;
                // 어제날짜와 일치하는 경우만 크롤링 
                } else if(dateA.compareTo(LocalDate.now().minusDays(1))==0){
                    NewsData newsData = new NewsData(1, newsDate.toString(), newsTime.toString(), newsTitle.toString(), newsUrl.toString());
                    this.newsDataDAO.insertNews(newsData);
                // 어제날짜보다 과거일경우 return (더이상 크롤링 할 필요가 없음)
                } else{
                    return;
                }
            }

            return;
            
            // 추후 Exception 찾아서 처리할것
        } catch(Exception e) {
            System.out.println("오류발생");
            return;
        }
    }
}