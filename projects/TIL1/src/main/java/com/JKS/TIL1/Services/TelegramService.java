package com.JKS.TIL1.Services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@PropertySource("classpath:telegram.properties")
public class TelegramService implements TelegramInterface{
    @Value("${TELEGRAM_URL}")
    private String telegramUrl;

    @Value("${TELEGRAM_TOKEN}")
    private String telegramToken;

    @Value("${TELEGRAM_USER}")
    private String telegramUser;

    public String getMessageContent(String situation){
        String message;

        switch(situation){
            // 뉴스포털 URL이 문제있는경우
            case "CrawlingURLFail":
                message = "크롤링할 페이지가 없습니다.";
                break;
            // 접근하는 CSSSeletor가 형식에 어긋날경우
            case "NotGoodCSSSelector":
                message = "잘못된 CSS Selector를 입력했습니다.";
                break;
            // 접근하고자 하는 element가 없는경우
            case "ChangedArchitecture":
                message = "크롤링할 페이지 구조가 변경되었습니다.";
                break;
            // element는 있으나 내용물이 없는경우
            case "NoContent":
                message = "css selector에 맞는 컨텐츠가 없습니다.";
                break;
            // Mail 발송 API가 미작동하는 경우
            case "MailAPIFail":
                message = "Mail발송 API가 동작하지 않습니다.";
                break;
            // Mail 발송 API는 작동하나 Mail발송이 실패한경우
            case "MailSendFail":
                message = "메일 발송이 실패했습니다.";
                break;
            // 메일발송에서 알수없는오류
            case "MailFail":
                message = "메일 발송에 알수없는 오류 발생";
                break;
            // gateway페이지 query로 넘긴 uid가 미확인된 경우
            case "NotReceiver":
                message = "gateway에 알수없는 접근입니다.";
                break;
            // 사용자가 접근하려는 url이 이미 변경된경우
            case "NewsIsChanged":
                message = "사용자가 접근한 뉴스가 변경되어 삭제되었습니다.";
                break;
            default:
                message = "알수없는 에러";
                break;
        }

        return message;
    }

    public void sendTelegramMessage(){
        String url = telegramUrl + telegramToken + "/" +"sendMessage";
        // System.out.println(url);
        RestTemplate restTemplate = new RestTemplate();

        // 참고로 http요청 시 https로 redirect되는 페이지의 경우 https경로 그대로 사용하도록한다.
        UriComponentsBuilder uri = UriComponentsBuilder.fromHttpUrl(url)
        .queryParam("chat_id",telegramUser)
        .queryParam("text","abcd");

        // RestTemplate 활용 http get method 실행
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri.toUriString(), String.class);
        // System.out.println(uri.toUriString());
        System.out.println(responseEntity.getStatusCode());




        // MultiValueMap<String,String> params = new LinkedMultiValueMap<String,String>();
        // params.add("chat_id",telegramUser);
        // params.add("text","스프링부트로 텔레그램 써보기");
        // ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, params,String.class);
    }
}