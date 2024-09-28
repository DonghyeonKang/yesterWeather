package com.example.yesterWeather.dto.api;

import com.example.yesterWeather.dto.GetWeatherByDateResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeatherApiRequest {
    private String baseDate; // 발표일자 - 20210628
    private String baseTime; // 발표시각 - 0600
    private String nx; // 예보지점 X 좌표값 - 55
    private String ny; // 예보지점 Y 좌표값 - 127
}
