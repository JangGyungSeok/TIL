package com.JKS.TIL1.Services;

public interface TelegramInterface {
    // 텔레그램 상황별 message String 반환
    public String getMessageContent(String sutiation);
    
    // 텔레그램 메시지 발송로직
    public void sendTelegramMessage();
}