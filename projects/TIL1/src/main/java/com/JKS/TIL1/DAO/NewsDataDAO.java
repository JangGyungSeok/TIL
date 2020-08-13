package com.JKS.TIL1.DAO;

import java.util.List;

import com.JKS.TIL1.DTO.NewsData;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface NewsDataDAO {
    List<NewsData> getAll();
    boolean insertNews(NewsData newsData);
}