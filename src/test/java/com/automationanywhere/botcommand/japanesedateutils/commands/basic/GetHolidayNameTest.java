package com.automationanywhere.botcommand.japanesedateutils.commands.basic;

import com.automationanywhere.botcommand.data.Value;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class GetHolidayNameTest {
    @Test
    public void GetHolidayNameTestWithOption1() {
        ZoneId zoneId = ZoneId.of("Asia/Tokyo");
        ZonedDateTime input = ZonedDateTime.of(2020, 1, 1, 0, 0, 0, 0, zoneId);
        String expectedOutput = "元日";

        GetHolidayName GetHolidayNameTest = new GetHolidayName();
        Value<String> result = GetHolidayNameTest.action(input);

        Assert.assertEquals(result.get(), expectedOutput);
    }

    @Test
    public void GetHolidayNameTestWithOption2() {
        ZoneId zoneId = ZoneId.of("Asia/Tokyo");
        ZonedDateTime input = ZonedDateTime.of(2020, 1, 2, 0, 0, 0, 0, zoneId);
        String expectedOutput = "";

        GetHolidayName GetHolidayNameTest = new GetHolidayName();
        Value<String> result = GetHolidayNameTest.action(input);

        Assert.assertEquals(result.get(), expectedOutput);
    }

    @Test
    public void GetHolidayNameTestWithOption3() {
        ZoneId zoneId = ZoneId.of("Asia/Tokyo");
        ZonedDateTime input = ZonedDateTime.of(2020, 1, 13, 0, 0, 0, 0, zoneId);
        String expectedOutput = "成人の日";

        GetHolidayName GetHolidayNameTest = new GetHolidayName();
        Value<String> result = GetHolidayNameTest.action(input);

        Assert.assertEquals(result.get(), expectedOutput);
    }

    @Test
    public void GetHolidayNameTestWithOption4() {
        ZoneId zoneId = ZoneId.of("Asia/Tokyo");
        ZonedDateTime input = ZonedDateTime.of(2020, 2, 11, 0, 0, 0, 0, zoneId);
        String expectedOutput = "建国記念日";

        GetHolidayName GetHolidayNameTest = new GetHolidayName();
        Value<String> result = GetHolidayNameTest.action(input);

        Assert.assertEquals(result.get(), expectedOutput);
    }

    @Test
    public void GetHolidayNameTestWithOption5() {
        ZoneId zoneId = ZoneId.of("Asia/Tokyo");
        ZonedDateTime input = ZonedDateTime.of(2020, 2, 23, 0, 0, 0, 0, zoneId);
        String expectedOutput = "天皇誕生日";

        GetHolidayName GetHolidayNameTest = new GetHolidayName();
        Value<String> result = GetHolidayNameTest.action(input);

        Assert.assertEquals(result.get(), expectedOutput);
    }

    @Test
    public void GetHolidayNameTestWithOption6() {
        ZoneId zoneId = ZoneId.of("Asia/Tokyo");
        ZonedDateTime input = ZonedDateTime.of(2020, 2, 24, 0, 0, 0, 0, zoneId);
        String expectedOutput = "振替休日";

        GetHolidayName GetHolidayNameTest = new GetHolidayName();
        Value<String> result = GetHolidayNameTest.action(input);

        Assert.assertEquals(result.get(), expectedOutput);
    }

    @Test
    public void GetHolidayNameTestWithOption7() {
        ZoneId zoneId = ZoneId.of("Asia/Tokyo");
        ZonedDateTime input = ZonedDateTime.of(2020, 1, 1, 0, 0, 0, 0, zoneId);
        String expectedOutput = "元日";

        GetHolidayName GetHolidayNameTest = new GetHolidayName();
        Value<String> result = GetHolidayNameTest.action(input);

        Assert.assertEquals(result.get(), expectedOutput);
    }

    @Test
    public void GetHolidayNameTestWithOption8() {
        ZoneId zoneId = ZoneId.of("Asia/Tokyo");
        ZonedDateTime input = ZonedDateTime.of(2020, 3, 20, 0, 0, 0, 0, zoneId);
        String expectedOutput = "春分の日";

        GetHolidayName GetHolidayNameTest = new GetHolidayName();
        Value<String> result = GetHolidayNameTest.action(input);

        Assert.assertEquals(result.get(), expectedOutput);
    }

    @Test
    public void GetHolidayNameTestWithOption9() {
        ZoneId zoneId = ZoneId.of("Asia/Tokyo");
        ZonedDateTime input = ZonedDateTime.of(2020, 4, 29, 0, 0, 0, 0, zoneId);
        String expectedOutput = "昭和の日";

        GetHolidayName GetHolidayNameTest = new GetHolidayName();
        Value<String> result = GetHolidayNameTest.action(input);

        Assert.assertEquals(result.get(), expectedOutput);
    }

    @Test
    public void GetHolidayNameTestWithOption10() {
        ZoneId zoneId = ZoneId.of("Asia/Tokyo");
        ZonedDateTime input = ZonedDateTime.of(2020, 5, 3, 0, 0, 0, 0, zoneId);
        String expectedOutput = "憲法記念日";

        GetHolidayName GetHolidayNameTest = new GetHolidayName();
        Value<String> result = GetHolidayNameTest.action(input);

        Assert.assertEquals(result.get(), expectedOutput);
    }

    @Test
    public void GetHolidayNameTestWithOption11() {
        ZoneId zoneId = ZoneId.of("Asia/Tokyo");
        ZonedDateTime input = ZonedDateTime.of(2020, 5, 4, 0, 0, 0, 0, zoneId);
        String expectedOutput = "みどりの日";

        GetHolidayName GetHolidayNameTest = new GetHolidayName();
        Value<String> result = GetHolidayNameTest.action(input);

        Assert.assertEquals(result.get(), expectedOutput);
    }

    @Test
    public void GetHolidayNameTestWithOption12() {
        ZoneId zoneId = ZoneId.of("Asia/Tokyo");
        ZonedDateTime input = ZonedDateTime.of(2020, 5, 5, 0, 0, 0, 0, zoneId);
        String expectedOutput = "こどもの日";

        GetHolidayName GetHolidayNameTest = new GetHolidayName();
        Value<String> result = GetHolidayNameTest.action(input);

        Assert.assertEquals(result.get(), expectedOutput);
    }

    @Test
    public void GetHolidayNameTestWithOption13() {
        ZoneId zoneId = ZoneId.of("Asia/Tokyo");
        ZonedDateTime input = ZonedDateTime.of(2020, 5, 6, 0, 0, 0, 0, zoneId);
        String expectedOutput = "振替休日";

        GetHolidayName GetHolidayNameTest = new GetHolidayName();
        Value<String> result = GetHolidayNameTest.action(input);

        Assert.assertEquals(result.get(), expectedOutput);
    }

    @Test
    public void GetHolidayNameTestWithOption14() {
        ZoneId zoneId = ZoneId.of("Asia/Tokyo");
        ZonedDateTime input = ZonedDateTime.of(2020, 7, 23, 0, 0, 0, 0, zoneId);
        String expectedOutput = "海の日";

        GetHolidayName GetHolidayNameTest = new GetHolidayName();
        Value<String> result = GetHolidayNameTest.action(input);

        Assert.assertEquals(result.get(), expectedOutput);
    }

    @Test
    public void GetHolidayNameTestWithOption15() {
        ZoneId zoneId = ZoneId.of("Asia/Tokyo");
        ZonedDateTime input = ZonedDateTime.of(2020, 7, 24, 0, 0, 0, 0, zoneId);
        String expectedOutput = "スポーツの日";

        GetHolidayName GetHolidayNameTest = new GetHolidayName();
        Value<String> result = GetHolidayNameTest.action(input);

        Assert.assertEquals(result.get(), expectedOutput);
    }

    @Test
    public void GetHolidayNameTestWithOption16() {
        ZoneId zoneId = ZoneId.of("Asia/Tokyo");
        ZonedDateTime input = ZonedDateTime.of(2020, 5, 4, 0, 0, 0, 0, zoneId);
        String expectedOutput = "みどりの日";

        GetHolidayName GetHolidayNameTest = new GetHolidayName();
        Value<String> result = GetHolidayNameTest.action(input);

        Assert.assertEquals(result.get(), expectedOutput);
    }

    @Test
    public void GetHolidayNameTestWithOption17() {
        ZoneId zoneId = ZoneId.of("Asia/Tokyo");
        ZonedDateTime input = ZonedDateTime.of(2020, 8, 10, 0, 0, 0, 0, zoneId);
        String expectedOutput = "山の日";

        GetHolidayName GetHolidayNameTest = new GetHolidayName();
        Value<String> result = GetHolidayNameTest.action(input);

        Assert.assertEquals(result.get(), expectedOutput);
    }

    @Test
    public void GetHolidayNameTestWithOption18() {
        ZoneId zoneId = ZoneId.of("Asia/Tokyo");
        ZonedDateTime input = ZonedDateTime.of(2020, 9, 21, 0, 0, 0, 0, zoneId);
        String expectedOutput = "敬老の日";

        GetHolidayName GetHolidayNameTest = new GetHolidayName();
        Value<String> result = GetHolidayNameTest.action(input);

        Assert.assertEquals(result.get(), expectedOutput);
    }

    @Test
    public void GetHolidayNameTestWithOption19() {
        ZoneId zoneId = ZoneId.of("Asia/Tokyo");
        ZonedDateTime input = ZonedDateTime.of(2020, 9, 22, 0, 0, 0, 0, zoneId);
        String expectedOutput = "秋分の日";

        GetHolidayName GetHolidayNameTest = new GetHolidayName();
        Value<String> result = GetHolidayNameTest.action(input);

        Assert.assertEquals(result.get(), expectedOutput);
    }

    @Test
    public void GetHolidayNameTestWithOption20() {
        ZoneId zoneId = ZoneId.of("Asia/Tokyo");
        ZonedDateTime input = ZonedDateTime.of(2020, 11, 3, 0, 0, 0, 0, zoneId);
        String expectedOutput = "文化の日";

        GetHolidayName GetHolidayNameTest = new GetHolidayName();
        Value<String> result = GetHolidayNameTest.action(input);

        Assert.assertEquals(result.get(), expectedOutput);
    }

    @Test
    public void GetHolidayNameTestWithOption21() {
        ZoneId zoneId = ZoneId.of("Asia/Tokyo");
        ZonedDateTime input = ZonedDateTime.of(2020, 11, 23, 0, 0, 0, 0, zoneId);
        String expectedOutput = "勤労感謝の日";

        GetHolidayName GetHolidayNameTest = new GetHolidayName();
        Value<String> result = GetHolidayNameTest.action(input);

        Assert.assertEquals(result.get(), expectedOutput);
    }

    @Test
    public void GetHolidayNameTestWithOption22() {
        ZoneId zoneId = ZoneId.of("Asia/Tokyo");
        ZonedDateTime input = ZonedDateTime.of(2019, 5, 1, 0, 0, 0, 0, zoneId);
        String expectedOutput = "新天皇即位の日";

        GetHolidayName GetHolidayNameTest = new GetHolidayName();
        Value<String> result = GetHolidayNameTest.action(input);

        Assert.assertEquals(result.get(), expectedOutput);
    }

    @Test
    public void GetHolidayNameTestWithOption23() {
        ZoneId zoneId = ZoneId.of("Asia/Tokyo");
        ZonedDateTime input = ZonedDateTime.of(2019, 10, 22, 0, 0, 0, 0, zoneId);
        String expectedOutput = "即位礼正殿の儀";

        GetHolidayName GetHolidayNameTest = new GetHolidayName();
        Value<String> result = GetHolidayNameTest.action(input);

        Assert.assertEquals(result.get(), expectedOutput);
    }
}
