package com.example.yesterWeather.dto.api;

import com.example.yesterWeather.constant.Category;
import com.example.yesterWeather.dto.GetWeatherByDateResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Forecast {
    private String baseDate;
    private String baseTime;
    private String category;
    private String fcstDate;
    private String fcstTime;
    private String fcstValue;
    private String nx;
    private String ny;
    private static final Map<String, String> skyCode = new HashMap<>();
    private static final Map<String, String> ptyCode = new HashMap<>();

    static {
        skyCode.put("1", "맑음");
        skyCode.put("3", "구름많음");
        skyCode.put("4", "흐림");
    }

    static {
        ptyCode.put("0", "없음");
        ptyCode.put("1", "비");
        ptyCode.put("2", "비/눈");
        ptyCode.put("3", "눈");
        ptyCode.put("4", "소나기");
    }

    public GetWeatherByDateResponse toGetWeatherByDateResponse() {
        if(category.equals("SKY")) {
            fcstValue = getSkyByCode(fcstValue);
        }
        if(category.equals("PTY") ) {
            fcstValue = getPtyByCode(fcstValue);
        }

        return GetWeatherByDateResponse.builder()
                .fcstDate(fcstDate)
                .fcstTime(fcstTime)
                .category(Category.getDescriptionByCategory(category))
                .fcstValue(fcstValue + Category.getDescriptionByCategory(category))
                .build();
    }

    private String getSkyByCode(String code) {
        return skyCode.get(code);
    }

    private String getPtyByCode(String code) {
        return ptyCode.get(code);
    }
}
