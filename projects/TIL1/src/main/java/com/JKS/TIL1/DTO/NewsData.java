package com.JKS.TIL1.DTO;

import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class NewsData {
    @Nullable
    private int idx;
    private String newsDate;
    private String newsTime;
    private String newsTitle;
    private String newsUrl;
}