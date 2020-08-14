package com.JKS.TIL1.Services;

import com.JKS.TIL1.DAO.NewsDataDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public class DashBoardService implements DashBoardInterface {
    @Autowired
    NewsDataDAO newsDataDAO;
    
    public void allNews(){
        this.newsDataDAO.getAll();
    }
}