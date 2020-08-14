package com.JKS.TIL1.Controllers;

import com.JKS.TIL1.Services.DashBoardInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DashBoardController {
    @Autowired
    DashBoardInterface dashBoardService;

    @GetMapping("/allNews")
    public String allNews(Model model){
        dashBoardService.allNews();

        return "home";
    }
}