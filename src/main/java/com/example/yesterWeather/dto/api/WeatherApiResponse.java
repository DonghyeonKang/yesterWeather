package com.example.yesterWeather.dto.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class WeatherApiResponse {
    private String serviceKey; // 서비스키
    private String pageNo; // 페이지 번호 - 1
    private String numOfRows; // 한 페이지 결과 수 - 1000
    private String dataType; // 응답자료형식(XML/JSON) Default:XML - 옵션
    private String base_date; // 발표일자 - 20210628
    private String base_time; // 발표시각 - 0600
    private String nx; // 예보지점 X 좌표값 - 55
    private String ny; // 예보지점 Y 좌표값 - 127
}
