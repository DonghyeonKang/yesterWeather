package com.example.yesterWeather.dto;

import com.example.yesterWeather.dto.api.WeatherApiRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetWeatherByDateRequest {
    private String baseDate; // 발표일자 - 20210628
    private String baseTime; // 발표시각 - 0600
    private String nx; // 예보지점 X 좌표값 - 55
    private String ny; // 예보지점 Y 좌표값 - 127

    public WeatherApiRequest toApiRequest() {
        return WeatherApiRequest.builder()
                .baseDate(baseDate)
                .baseTime(baseTime)
                .nx(nx)
                .ny(ny)
                .build();
    }
}
