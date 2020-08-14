package com.JKS.TIL1.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

// constructor 자동생성 ( 모든 멤버변수를 초기화하는 )
@AllArgsConstructor
// getter, setter 한번에 자동생성 (camelCase 네이밍방식을 따름)
@Data
public class CrawlingLog {
    int idx;
    String crawlingDate;
    String startTime;
    String endTime;
    int crawlingCount;
}