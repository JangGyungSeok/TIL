package com.JKS.TIL1.Controllers;

import com.JKS.TIL1.Services.CrawlingInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CrawlingController {

    @Autowired
    CrawlingInterface crawlingService;

    @GetMapping("/Crawling")
    @ResponseBody
    public String crawling(){
        crawlingService.crawlingNews();

        return "true";
    }
}