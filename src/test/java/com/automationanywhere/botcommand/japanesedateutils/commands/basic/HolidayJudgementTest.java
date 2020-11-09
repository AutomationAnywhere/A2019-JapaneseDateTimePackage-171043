package com.automationanywhere.botcommand.japanesedateutils.commands.basic;

import com.automationanywhere.botcommand.data.Value;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class HolidayJudgementTest {
    @Test
    public void HolidayJudgementTestTestWithOption1() {
        ZoneId zoneId = ZoneId.of("Asia/Tokyo");
        ZonedDateTime input = ZonedDateTime.of(2020, 1, 1, 0, 0, 0, 0, zoneId);
        Boolean expectedOutput = true;

        HolidayJudgement HolidayJudgementTest = new HolidayJudgement();
        Value<Boolean> result = HolidayJudgementTest.action(input, "Holiday");

        Assert.assertEquals(result.get(), expectedOutput);
    }

}
