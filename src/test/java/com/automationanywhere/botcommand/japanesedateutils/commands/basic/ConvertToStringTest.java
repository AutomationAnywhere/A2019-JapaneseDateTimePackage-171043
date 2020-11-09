package com.automationanywhere.botcommand.japanesedateutils.commands.basic;

import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.exception.BotCommandException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ConvertToStringTest {
    @Test
    public void ConvertToStringTestWithOption1(){
        ZoneId zoneId = ZoneId.of("Asia/Tokyo");
        ZonedDateTime date = ZonedDateTime.of(2020, 5, 1, 12, 0, 0, 0, zoneId);

        String expectedOutput = "令和2年5月1日";

        ConvertToString testConvertToString = new ConvertToString();
        String type ="prebuilt";
        String format = "Gy年M月d日";
        String customFormat = "";
        Value<String> result = testConvertToString.action(date,type,format,customFormat);

        Assert.assertEquals(result.get(), expectedOutput);
    }

    @Test
    public void ConvertToStringTestWithOption2(){
        ZoneId zoneId = ZoneId.of("Asia/Tokyo");
        ZonedDateTime date = ZonedDateTime.of(2020, 5, 1, 12, 0, 0, 0, zoneId);

        String expectedOutput = "令和02年05月01日";

        ConvertToString testConvertToString = new ConvertToString();
        String type ="prebuilt";
        String format = "Gyy年MM月dd日";
        String customFormat = "";
        Value<String> result = testConvertToString.action(date,type,format,customFormat);

        Assert.assertEquals(result.get(), expectedOutput);
    }

    @Test
    public void ConvertToStringTestWithOption3(){
        ZoneId zoneId = ZoneId.of("Asia/Tokyo");
        ZonedDateTime date = ZonedDateTime.of(2020, 5, 1, 12, 0, 0, 0, zoneId);

        String expectedOutput = "R2.5.1";

        ConvertToString testConvertToString = new ConvertToString();
        String type ="prebuilt";
        String format = "GGGGGy.M.d";
        String customFormat = "";
        Value<String> result = testConvertToString.action(date,type,format,customFormat);

        Assert.assertEquals(result.get(), expectedOutput);
    }

    @Test
    public void ConvertToStringTestWithOption4(){
        ZoneId zoneId = ZoneId.of("Asia/Tokyo");
        ZonedDateTime date = ZonedDateTime.of(2020, 5, 1, 12, 0, 0, 0, zoneId);

        String expectedOutput = "R02.05.01";

        ConvertToString testConvertToString = new ConvertToString();
        String type ="prebuilt";
        String format = "GGGGGyy.MM.dd";
        String customFormat = "";
        Value<String> result = testConvertToString.action(date,type,format,customFormat);

        Assert.assertEquals(result.get(), expectedOutput);
    }


    @Test
    public void ConvertToStringTestWithOption5(){
        ZoneId zoneId = ZoneId.of("Asia/Tokyo");
        ZonedDateTime date = ZonedDateTime.of(2020, 5, 1, 12, 0, 0, 0, zoneId);

        String expectedOutput = "令02.05.01";

        ConvertToString testConvertToString = new ConvertToString();
        String type ="prebuilt";
        String format = "1Gyy.MM.dd";
        String customFormat = "";
        Value<String> result = testConvertToString.action(date,type,format,customFormat);

        Assert.assertEquals(result.get(), expectedOutput);
    }

    @Test
    public void ConvertToStringTestWithOption6(){
        ZoneId zoneId = ZoneId.of("Asia/Tokyo");
        ZonedDateTime date = ZonedDateTime.of(2020, 5, 1, 12, 0, 0, 0, zoneId);

        String expectedOutput = "令2.5.1";

        ConvertToString testConvertToString = new ConvertToString();
        String type ="prebuilt";
        String format = "1Gy.M.d";
        String customFormat = "";
        Value<String> result = testConvertToString.action(date,type,format,customFormat);

        Assert.assertEquals(result.get(), expectedOutput);
    }
    @Test
    public void ConvertToStringTestWithOption7(){
        ZoneId zoneId = ZoneId.of("Asia/Tokyo");
        ZonedDateTime date = ZonedDateTime.of(2019, 5, 1, 12, 0, 0, 0, zoneId);

        String expectedOutput = "令和元年五月一日";

        ConvertToString testConvertToString = new ConvertToString();
        String type ="prebuilt";
        String format = "kanji";
        String customFormat = "";
        Value<String> result = testConvertToString.action(date,type,format,customFormat);

        Assert.assertEquals(result.get(), expectedOutput);
    }
}
