package com.example.yesterWeather.service;
import com.example.yesterWeather.dto.api.Forecast;
import com.example.yesterWeather.dto.api.WeatherApiRequest;
import com.example.yesterWeather.dto.api.WeatherApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.InputStreamReader;
import java.net.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class WeatherApi {
    public List<Forecast> getWeather(WeatherApiRequest request) throws URISyntaxException, IOException {
        /*URL*/
        String urlString = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst" + "?" + URLEncoder.encode("serviceKey", StandardCharsets.UTF_8) + "=LxUHb5pgKSDlondfy%2BqNcIALltYINPF2PgYmgdG8B4Ch2GxXjl3wwGKxKF%2F%2Br5SQXOdFREoVGxI7%2FozqXvC8mw%3D%3D" + /*Service Key*/
                "&" + URLEncoder.encode("pageNo", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("1", StandardCharsets.UTF_8) +
                "&" + URLEncoder.encode("numOfRows", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("14", StandardCharsets.UTF_8) + /*한 페이지 결과 수*/
                "&" + URLEncoder.encode("dataType", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("JSON", StandardCharsets.UTF_8) + /*요청자료형식(XML/JSON) Default: XML*/
                "&" + URLEncoder.encode("base_date", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("20240928", StandardCharsets.UTF_8) + /*‘21년 6월 28일 발표*/
                "&" + URLEncoder.encode("base_time", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("0500", StandardCharsets.UTF_8) + /*06시 발표(정시단위) */
                "&" + URLEncoder.encode("nx", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("55", StandardCharsets.UTF_8) + /*예보지점의 X 좌표값*/
                "&" + URLEncoder.encode("ny", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("127", StandardCharsets.UTF_8); /*예보지점의 Y 좌표값*/

        System.out.println(urlString);
        URI uri = new URI(urlString);
        URL url = uri.toURL();

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;

        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }

        rd.close();
        conn.disconnect();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(sb.toString());
        ArrayNode items = (ArrayNode) root.path("response").path("body").path("items").path("item");

        List<Forecast> forecasts = new ArrayList<>();
        for (JsonNode item : items) {
            Forecast forecast = Forecast.builder()
                    .baseDate(item.get("baseDate").asText())
                    .baseTime(item.get("baseTime").asText())
                    .category(item.get("category").asText())
                    .fcstDate(item.get("fcstDate").asText())
                    .fcstTime(item.get("fcstTime").asText())
                    .fcstValue(item.get("fcstValue").asText())
                    .nx(item.get("nx").asText())
                    .ny(item.get("ny").asText())
                    .build();
            forecasts.add(forecast);
        }

        return forecasts;
    }
}
