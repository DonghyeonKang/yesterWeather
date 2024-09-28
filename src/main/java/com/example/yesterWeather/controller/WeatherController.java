package com.example.yesterWeather.controller;

import com.example.yesterWeather.constant.Category;
import com.example.yesterWeather.dto.GetWeatherByDateRequest;
import com.example.yesterWeather.dto.GetWeatherByDateResponse;
import com.example.yesterWeather.dto.api.Forecast;
import com.example.yesterWeather.dto.api.WeatherApiRequest;
import com.example.yesterWeather.service.WeatherApi;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/weather")
public class WeatherController {
    private final WeatherApi weatherApi;

    @GetMapping("/detail")
    public List<GetWeatherByDateResponse> getWeatherByDate(@ModelAttribute GetWeatherByDateRequest request) throws URISyntaxException, IOException {
        // api 요청
        WeatherApiRequest apiRequest = request.toApiRequest();
        List<Forecast> data = weatherApi.getWeather(apiRequest);

        // 데이터 파싱
        List<GetWeatherByDateResponse> result = new ArrayList<>();
        for(Forecast forecast : data) {
            result.add(forecast.toGetWeatherByDateResponse());
        }

        // api 로 가져온 데이터 응답
        return result;
    }
}
