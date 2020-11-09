package com.automationanywhere.botcommand.japanesedateutils.commands.basic;

import com.automationanywhere.botcommand.data.Value;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.DateTimeException;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ConvertToDateTest {
    @Test
    public void ConvertToStringTestWithOption1(){
        ZoneId zoneId = ZoneId.of("Asia/Tokyo");
        String strDate = "令和2年5月1日";
        ZonedDateTime expectedOutput = ZonedDateTime.of(2020, 5, 1, 0, 0, 0, 0, zoneId);

        ConvertToDate testConvertToDate = new ConvertToDate();
        String type ="prebuilt";
        String format = "Gy年M月d日";
        String customFormat = "";
        Value<ZonedDateTime> result = testConvertToDate.action(strDate,type,format,customFormat);

        Assert.assertEquals(result.get(), expectedOutput);
    }

    @Test
    public void ConvertToStringTestWithOption2(){
        ZoneId zoneId = ZoneId.of("Asia/Tokyo");
        String strDate = "令02.05.01";
        ZonedDateTime expectedOutput = ZonedDateTime.of(2020, 5, 1, 0, 0, 0, 0, zoneId);

        ConvertToDate testConvertToDate = new ConvertToDate();
        String type ="prebuilt";
        String format = "1Gyy.MM.dd";
        String customFormat = "";
        Value<ZonedDateTime> result = testConvertToDate.action(strDate,type,format,customFormat);

        Assert.assertEquals(result.get(), expectedOutput);
    }

    @Test
    public void ConvertToStringTestWithOption3(){
        ZoneId zoneId = ZoneId.of("Asia/Tokyo");
        String strDate = "令2.5.1";
        ZonedDateTime expectedOutput = ZonedDateTime.of(2020, 5, 1, 0, 0, 0, 0, zoneId);

        ConvertToDate testConvertToDate = new ConvertToDate();
        String type ="prebuilt";
        String format = "1Gy.M.d";
        String customFormat = "";
        Value<ZonedDateTime> result = testConvertToDate.action(strDate,type,format,customFormat);

        Assert.assertEquals(result.get(), expectedOutput);
    }

//    @Test
    public void ConvertToStringTestWithOption4(){
        ZoneId zoneId = ZoneId.of("Asia/Tokyo");
        String strDate = "令和元年五月一日";
        ZonedDateTime expectedOutput = ZonedDateTime.of(2020, 5, 1, 0, 0, 0, 0, zoneId);

        ConvertToDate testConvertToDate = new ConvertToDate();
        String type ="prebuilt";
        String format = "kanji";
        String customFormat = "";
        Value<ZonedDateTime> result = testConvertToDate.action(strDate,type,format,customFormat);

        Assert.assertEquals(result.get(), expectedOutput);
    }
}
