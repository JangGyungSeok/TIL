package com.JKS.TIL1.Controllers;

import com.JKS.TIL1.Services.TelegramService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    
    @Autowired
    TelegramService telegramService;

    @GetMapping("/")
    public String home(){
        telegramService.sendTelegramMessage();
        return "home";
    }


}