package com.JKS.TIL1.DTO;

import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Data;

// constructor 자동생성 ( 모든 멤버변수를 초기화하는 )
@AllArgsConstructor
// getter, setter 한번에 자동생성 (camelCase 네이밍방식을 따름)
@Data
public class NewsData {

    // 뉴스 인덱스
    @Nullable
    private int idx;
    // 뉴스 날짜
    private String newsDate;
    // 뉴스 시간
    private String newsTime;
    // 뉴스 제목
    private String newsTitle;
    // 뉴스 URL
    private String newsUrl;
}