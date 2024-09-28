package com.example.yesterWeather.constant;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum Category {
    POP("강수확률", "%", 8),
    PTY("강수형태", "", 4),
    PCP("1시간 강수량", "mm", 12),
    REH("습도", "%", 8),
    SNO("1시간 신적설", "cm", 10),
    SKY("하늘상태", "", 4),
    TMP("1시간 기온", "℃", 10),
    TMN("일 최저기온", "℃", 10),
    TMX("일 최고기온", "℃", 10),
    UUU("풍속(동서성분)", "m/s", 12),
    VVV("풍속(남북성분)", "m/s", 12),
    WAV("파고", "M", 10),
    VEC("풍향", "deg", 10),
    WSD("풍속", "m/s", 10);

    private final String description;
    private final String unit;
    private final int bit;
    private static final Map<String, Category> lookup = new HashMap<>();

    Category(String description, String unit, int bit) {
        this.description = description;
        this.unit = unit;
        this.bit = bit;
    }

    static {
        for (Category category : Category.values()) {
            lookup.put(category.name(), category);
        }
    }

    public static String getDescriptionByCategory(String category) {
        return lookup.get(category).description;
    }

    public static String getUnitByCategory(String category) {
        return lookup.get(category).unit;
    }
}
