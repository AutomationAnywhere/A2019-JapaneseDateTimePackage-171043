package com.automationanywhere.botcommand.japanesedateutils.commands.basic;

import com.automationanywhere.botcommand.data.Value;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class GetBeforeAfterHolidayTest {
    @Test
    public void GetBeforeAfterHolidayTestWithOption1(){
        ZoneId zoneId = ZoneId.of("Asia/Tokyo");
        ZonedDateTime input = ZonedDateTime.of(2020, 5, 1, 0, 0, 0, 0, zoneId);
        ZonedDateTime expectedOutput = ZonedDateTime.of(2020, 5, 1, 0, 0, 0, 0, zoneId);

        GetBeforeAfterHoliday GetBeforeAfterHolidayTest = new GetBeforeAfterHoliday();
        Value<ZonedDateTime> result = GetBeforeAfterHolidayTest.action(input,"Holiday","After");

        Assert.assertEquals(result.get(), expectedOutput);
    }
    @Test
    public void GetBeforeAfterHolidayTestWithOption2(){
        ZoneId zoneId = ZoneId.of("Asia/Tokyo");
        ZonedDateTime input = ZonedDateTime.of(2020, 1, 1, 0, 0, 0, 0, zoneId);
        ZonedDateTime expectedOutput = ZonedDateTime.of(2020, 1, 2, 0, 0, 0, 0, zoneId);

        GetBeforeAfterHoliday GetBeforeAfterHolidayTest = new GetBeforeAfterHoliday();
        Value<ZonedDateTime> result = GetBeforeAfterHolidayTest.action(input,"Holiday","After");

        Assert.assertEquals(result.get(), expectedOutput);
    }
    @Test
    public void GetBeforeAfterHolidayTestWithOption3(){
        ZoneId zoneId = ZoneId.of("Asia/Tokyo");
        ZonedDateTime input = ZonedDateTime.of(2020, 1, 1, 0, 0, 0, 0, zoneId);
        ZonedDateTime expectedOutput = ZonedDateTime.of(2019, 12, 31, 0, 0, 0, 0, zoneId);

        GetBeforeAfterHoliday GetBeforeAfterHolidayTest = new GetBeforeAfterHoliday();
        Value<ZonedDateTime> result = GetBeforeAfterHolidayTest.action(input,"Holiday","Before");

        Assert.assertEquals(result.get(), expectedOutput);
    }
    @Test
    public void GetBeforeAfterHolidayTestWithOption4(){
        ZoneId zoneId = ZoneId.of("Asia/Tokyo");
        ZonedDateTime input = ZonedDateTime.of(2020, 6, 14, 0, 0, 0, 0, zoneId);
        ZonedDateTime expectedOutput = ZonedDateTime.of(2020, 6, 12, 0, 0, 0, 0, zoneId);

        GetBeforeAfterHoliday GetBeforeAfterHolidayTest = new GetBeforeAfterHoliday();
        Value<ZonedDateTime> result = GetBeforeAfterHolidayTest.action(input,"Holiday","Before");

        Assert.assertEquals(result.get(), expectedOutput);
    }
    @Test
    public void GetBeforeAfterHolidayTestWithOption5(){
        ZoneId zoneId = ZoneId.of("Asia/Tokyo");
        ZonedDateTime input = ZonedDateTime.of(2020, 6, 13, 0, 0, 0, 0, zoneId);
        ZonedDateTime expectedOutput = ZonedDateTime.of(2020, 6, 12, 0, 0, 0, 0, zoneId);

        GetBeforeAfterHoliday GetBeforeAfterHolidayTest = new GetBeforeAfterHoliday();
        Value<ZonedDateTime> result = GetBeforeAfterHolidayTest.action(input,"Holiday","Before");

        Assert.assertEquals(result.get(), expectedOutput);
    }
    @Test
    public void GetBeforeAfterHolidayTestWithOption6(){
        ZoneId zoneId = ZoneId.of("Asia/Tokyo");
        ZonedDateTime input = ZonedDateTime.of(2020, 6, 13, 0, 0, 0, 0, zoneId);
        ZonedDateTime expectedOutput = ZonedDateTime.of(2020, 6, 15, 0, 0, 0, 0, zoneId);

        GetBeforeAfterHoliday GetBeforeAfterHolidayTest = new GetBeforeAfterHoliday();
        Value<ZonedDateTime> result = GetBeforeAfterHolidayTest.action(input,"Holiday","After");

        Assert.assertEquals(result.get(), expectedOutput);
    }


}
