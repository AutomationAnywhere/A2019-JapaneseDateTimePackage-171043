package com.automationanywhere.botcommand.japanesedateutils.commands.basic.helper;

import java.time.chrono.JapaneseChronology;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.ResolverStyle;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * help kanji formart
 *
 * Inspired : https://qiita.com/yamadamn/items/56e7370bae2ceaec55d5
 **/

public class KanjiDateTimeFormatter {

public static DateTimeFormatter get() {
    return get("LENIENT");
}

public static DateTimeFormatter get(String mode){
    String[] kanjiNumBase = {"〇", "一", "二", "三", "四", "五", "六", "七", "八", "九"};

    // 1(一)から99(九十九)まで漢数字を設定
        Map<Long, String> kanjiNumMap = new HashMap<>();
        for (int num = 1; num < 100; num++) {
            if (num < 10) {
                kanjiNumMap.put(Long.valueOf(num), kanjiNumBase[num]);
                continue;
            }

            int tens = num / 10;
            int ones = num % 10;
            StringBuilder kanjiNum = new StringBuilder();
            if (tens > 1) {
                kanjiNum.append(kanjiNumBase[tens]);
            }
            kanjiNum.append("十");
            if (ones > 0) {
                kanjiNum.append(kanjiNumBase[ones]);
            }
            kanjiNumMap.put(Long.valueOf(num), kanjiNum.toString());
        }

    // 年は元年・百年に対応
        Map<Long, String> yearMap = new HashMap<>(kanjiNumMap);
        yearMap.put(Long.valueOf(1), "元");
        yearMap.put(Long.valueOf(100), "百");

        DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder();
        builder.appendText(ChronoField.ERA, TextStyle.FULL);
        builder.appendText(ChronoField.YEAR_OF_ERA, yearMap);
        builder.appendLiteral("年");
        builder.appendText(ChronoField.MONTH_OF_YEAR, kanjiNumMap);
        builder.appendLiteral("月");
        builder.appendText(ChronoField.DAY_OF_MONTH, kanjiNumMap);
        builder.appendLiteral("日");

        switch (mode){
            case "STRICT":
                return builder.toFormatter(Locale.JAPAN)
                        .withChronology(JapaneseChronology.INSTANCE)
                        .withResolverStyle(ResolverStyle.STRICT);
            case "SMART":
                return builder.toFormatter(Locale.JAPAN)
                        .withChronology(JapaneseChronology.INSTANCE)
                        .withResolverStyle(ResolverStyle.SMART);
            case "LENIENT":
            default:
                return builder.toFormatter(Locale.JAPAN)
                        .withChronology(JapaneseChronology.INSTANCE)
                        .withResolverStyle(ResolverStyle.LENIENT);
        }

    }

    public static class get {
    }
}
