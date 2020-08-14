package com.JKS.TIL1.Controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.JKS.TIL1.DAO.NewsDataDAO;
import com.JKS.TIL1.DTO.NewsData;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MybatisController {
    @Autowired
    private NewsDataDAO newsDataDAO;

    @GetMapping("/mybatisTest")
    public String mybatisTest(){

        return LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        try{
            List<NewsData> newsDataList;
            newsDataList = newsDataDAO.getAll(); 
            return newsDataList.get(0).getNewsTitle();

        // return newsDataList.get(0).getNewsTitle();
        } catch(Exception e){
            System.out.println(e.getMessage());
            return "..";
        }
    }

    @GetMapping("/test2")
    @ResponseBody
    public String test2(){

        try{
            LocalDate timeA = LocalDate.parse("2020-08-13");
            LocalDate timeC = LocalDate.parse("2020-08-13");
            LocalDate timeB = LocalDate.parse("2020-08-14");
            System.out.println(timeA.compareTo(timeC));
            System.out.println(timeA);
        } catch(Exception e){
            return "실패";
        }
        

        return "성공";
    }
}