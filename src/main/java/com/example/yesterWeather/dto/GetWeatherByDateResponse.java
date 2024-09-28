package com.example.yesterWeather.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GetWeatherByDateResponse {
    private String fcstDate;
    private String fcstTime;
    private String category;
    private String fcstValue;
}
