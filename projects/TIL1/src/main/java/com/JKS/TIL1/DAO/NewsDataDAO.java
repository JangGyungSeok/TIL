package com.JKS.TIL1.DAO;

import java.util.List;

import com.JKS.TIL1.DTO.NewsData;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface NewsDataDAO {

    // 크롤링한 기사 전체 조회
    List<NewsData> getAll();

    // 뉴스 날짜에 해당하는 기사 조회
    NewsData getNewsByNewsDate(String newsDate);

    // 크롤링한 기사 적재
    boolean insertNews(NewsData newsData);
}